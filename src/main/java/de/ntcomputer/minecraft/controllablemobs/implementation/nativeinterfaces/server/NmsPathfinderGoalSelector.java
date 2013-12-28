package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_7_R1.PathfinderGoal;
import net.minecraft.server.v1_7_R1.PathfinderGoalSelector;

import org.bukkit.craftbukkit.v1_7_R1.util.UnsafeList;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NmsPathfinderGoalSelector {
	public final NativeFieldObject<PathfinderGoalSelector, UnsafeList<Object>> FIELD_GOALITEMS = new NativeFieldObject<PathfinderGoalSelector, UnsafeList<Object>>(PathfinderGoalSelector.class, "b");
	public final NativeFieldObject<PathfinderGoalSelector, UnsafeList<Object>> FIELD_ACTIVEGOALITEMS = new NativeFieldObject<PathfinderGoalSelector, UnsafeList<Object>>(PathfinderGoalSelector.class, "c");
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
