package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldInt;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;

public final class NotchInterfacePathfinderGoalSelectorItem {
	public final NativeFieldObject<Object, PathfinderGoal> FIELD_GOAL = new NativeFieldObject<Object, PathfinderGoal>("a");
	public final NativeFieldInt<Object> FIELD_PRIORITY = new NativeFieldInt<Object>("b");

}
