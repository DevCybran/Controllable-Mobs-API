package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_8_R3.Navigation;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import net.minecraft.server.v1_8_R3.PathfinderGoalBreakDoor;
import net.minecraft.server.v1_8_R3.PathfinderGoalDoorInteract;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalOpenDoor;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
//import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

@SuppressWarnings("unchecked")
public final class AIComponentHandlers {
	private static final Map<Class<? extends PathfinderGoal>, AIComponentListener<?>> handlerMap = new HashMap<Class<? extends PathfinderGoal>, AIComponentListener<?>>();

	private AIComponentHandlers() {
		throw new AssertionError();
	}
	
	private static <T extends PathfinderGoal> void add(AIComponentListener<T> listener, Class<? extends T>... goalClasses) {
		for(Class<? extends T> goalClass: goalClasses) {
			handlerMap.put(goalClass, listener);
		}
	}
	
	private static <T extends PathfinderGoal> void add(Class<? extends T> goalClass, AIComponentListener<T> listener) {
		handlerMap.put(goalClass, listener);
	}
	
	static {
		AIComponentListener<PathfinderGoalDoorInteract> closedDoorListener = new AIComponentListener<PathfinderGoalDoorInteract>() {
			@Override
			public void onAdd(CraftControllableMob<?> mob, PathfinderGoalDoorInteract goal) {
				((Navigation) mob.nmsEntity.getNavigation()).a(true);
				//NativeInterfaces.NAVIGATION.FIELD_USECLOSEDDOOR.set(mob.nmsEntity.getNavigation(), true);
			}
			@Override
			public void onRemoved(CraftControllableMob<?> mob, PathfinderGoalDoorInteract goal) {
				if(!mob.getAI().hasBehavior(AIType.ACTION_DOOROPEN, AIType.ACTION_DOORBREAK)) {
					((Navigation) mob.nmsEntity.getNavigation()).a(false);
					//NativeInterfaces.NAVIGATION.FIELD_USECLOSEDDOOR.set(mob.nmsEntity.getNavigation(), false);
				}
			}
		};
		add(closedDoorListener, PathfinderGoalOpenDoor.class, PathfinderGoalBreakDoor.class);
		
		add(PathfinderGoalFloat.class, new AIComponentListener<PathfinderGoalFloat>() {
			@Override
			public void onAdd(CraftControllableMob<?> mob, PathfinderGoalFloat goal) {
				((Navigation) mob.nmsEntity.getNavigation()).b(true);
				// TODO: NativeInterfaces.NAVIGATION.FIELD_CANSWIM.set(mob.nmsEntity.getNavigation(), true);
			}
			@Override
			public void onRemoved(CraftControllableMob<?> mob, PathfinderGoalFloat goal) {
				if(!mob.getAI().hasBehavior(AIType.MOVE_SWIM)) {
					((Navigation) mob.nmsEntity.getNavigation()).b(false);
					// TODO: NativeInterfaces.NAVIGATION.FIELD_CANSWIM.set(mob.nmsEntity.getNavigation(), false);
				}
			}
		});
	}
	
	static <T extends PathfinderGoal> void handleAdd(CraftControllableMob<?> mob, T goal) {
		AIComponentListener<T> listener = (AIComponentListener<T>) handlerMap.get(goal.getClass());
		if(listener!=null) {
			listener.onAdd(mob, goal);
		}
	}
	
	static <T extends PathfinderGoal> void handleRemoved(CraftControllableMob<?> mob, T goal) {
		AIComponentListener<T> listener = (AIComponentListener<T>) handlerMap.get(goal.getClass());
		if(listener!=null) {
			listener.onRemoved(mob, goal);
		}
	}

}
