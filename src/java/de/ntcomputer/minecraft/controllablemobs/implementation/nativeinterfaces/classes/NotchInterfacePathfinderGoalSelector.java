package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalSelector;

import org.bukkit.craftbukkit.v1_6_R3.util.UnsafeList;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NotchInterfacePathfinderGoalSelector {
	public final NativeFieldObject<PathfinderGoalSelector, UnsafeList<Object>> FIELD_GOALITEMS = new NativeFieldObject<PathfinderGoalSelector, UnsafeList<Object>>(PathfinderGoalSelector.class, "a");
	public final NativeFieldObject<PathfinderGoalSelector, UnsafeList<Object>> FIELD_ACTIVEGOALITEMS = new NativeFieldObject<PathfinderGoalSelector, UnsafeList<Object>>(PathfinderGoalSelector.class, "b");
	public final AddPathfinderGoal METHOD_ADDPATHFINDERGOAL = new AddPathfinderGoal();
	
	public class AddPathfinderGoal extends NativeMethodPublic {
		public void invoke(PathfinderGoalSelector selector, int priority, PathfinderGoal goal) {
			try {
				selector.a(priority, goal);
			} catch(Throwable e) {
				this.handleException(e);
			}
		}
	}

}
