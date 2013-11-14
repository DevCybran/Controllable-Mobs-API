package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalBreakDoor;
import net.minecraft.server.v1_6_R3.PathfinderGoalDoorInteract;
import net.minecraft.server.v1_6_R3.PathfinderGoalOpenDoor;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public final class AIComponentHandlers {
	private static final Map<Class<? extends PathfinderGoal>, AIComponentListener<?>> handlerMap = new HashMap<Class<? extends PathfinderGoal>, AIComponentListener<?>>();

	private AIComponentHandlers() {
		throw new AssertionError();
	}
	
	private static <T extends PathfinderGoal> void add(Class<T> goalClass, AIComponentListener<? super T> listener) {
		handlerMap.put(goalClass, listener);
	}
	
	static {
		AIComponentListener<PathfinderGoalDoorInteract> closedDoorListener = new AIComponentListener<PathfinderGoalDoorInteract>() {
			@Override
			public void onAdd(CraftControllableMob<?> mob, PathfinderGoalDoorInteract goal) {
				NativeInterfaces.NAVIGATION.FIELD_USECLOSEDDOOR.set(mob.nmsEntity.getNavigation(), true);
			}
			@Override
			public void onRemoved(CraftControllableMob<?> mob, PathfinderGoalDoorInteract goal) {
				if(!mob.getAI().hasBehavior(AIType.ACTION_DOOROPEN, AIType.ACTION_DOORBREAK)) {
					NativeInterfaces.NAVIGATION.FIELD_USECLOSEDDOOR.set(mob.nmsEntity.getNavigation(), false);
				}
			}
		};
		add(PathfinderGoalOpenDoor.class, closedDoorListener);
		add(PathfinderGoalBreakDoor.class, closedDoorListener);
	}
	
	@SuppressWarnings("unchecked")
	static <T extends PathfinderGoal> void handleAdd(CraftControllableMob<?> mob, T goal) {
		AIComponentListener<T> listener = (AIComponentListener<T>) handlerMap.get(goal.getClass());
		if(listener!=null) {
			listener.onAdd(mob, goal);
		}
	}
	
	@SuppressWarnings("unchecked")
	static <T extends PathfinderGoal> void handleRemoved(CraftControllableMob<?> mob, T goal) {
		AIComponentListener<T> listener = (AIComponentListener<T>) handlerMap.get(goal.getClass());
		if(listener!=null) {
			listener.onRemoved(mob, goal);
		}
	}

}
