package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.CBInterfaceJavaPluginLoader;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.CBInterfacePluginClassLoader;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceControllerJump;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceControllerLook;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceEntity;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceEntityLiving;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceNavigation;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfacePathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfacePathfinderGoalSelector;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfacePathfinderGoalSelectorItem;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceWorld;

public final class NativeInterfaces {
	public static final NotchInterfaceControllerJump CONTROLLERJUMP = new NotchInterfaceControllerJump();
	public static final NotchInterfaceControllerLook CONTROLLERLOOK = new NotchInterfaceControllerLook();
	public static final NotchInterfaceEntity ENTITY = new NotchInterfaceEntity();
	public static final NotchInterfaceEntityLiving ENTITYLIVING = new NotchInterfaceEntityLiving();
	public static final CBInterfaceJavaPluginLoader JAVAPLUGINLOADER = new CBInterfaceJavaPluginLoader();
	public static final NotchInterfaceNavigation NAVIGATION = new NotchInterfaceNavigation();
	public static final NotchInterfacePathfinderGoal PATHFINDERGOAL = new NotchInterfacePathfinderGoal();
	public static final NotchInterfacePathfinderGoalSelector PATHFINDERGOALSELECTOR = new NotchInterfacePathfinderGoalSelector();
	public static final NotchInterfacePathfinderGoalSelectorItem PATHFINDERGOALSELECTORITEM = new NotchInterfacePathfinderGoalSelectorItem();
	public static final CBInterfacePluginClassLoader PLUGINCLASSLOADER = new CBInterfacePluginClassLoader();
	public static final NotchInterfaceWorld WORLD = new NotchInterfaceWorld();
	
	private NativeInterfaces() {
		throw new AssertionError();
	}

}
