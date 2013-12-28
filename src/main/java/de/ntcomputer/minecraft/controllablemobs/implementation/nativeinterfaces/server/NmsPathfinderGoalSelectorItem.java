package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_7_R1.PathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldInt;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;

public final class NmsPathfinderGoalSelectorItem {
	public final NativeFieldObject<Object, PathfinderGoal> FIELD_GOAL = new NativeFieldObject<Object, PathfinderGoal>("a");
	public final NativeFieldInt<Object> FIELD_PRIORITY = new NativeFieldInt<Object>("b");

}
