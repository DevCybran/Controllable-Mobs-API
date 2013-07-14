package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.CBInterfaceJavaPluginLoader;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.CBInterfacePluginClassLoader;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NmsAttributeModifiable;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NmsAttributeModifier;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NmsAttributeRanged;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NmsEntityInsentient;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NmsGenericAttributes;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NmsIAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceControllerJump;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceControllerLook;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceEntity;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceNavigation;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfacePathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfacePathfinderGoalSelector;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfacePathfinderGoalSelectorItem;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes.NotchInterfaceWorld;

public final class NativeInterfaces {
	public static final NotchInterfaceControllerJump CONTROLLERJUMP = new NotchInterfaceControllerJump();
	public static final NotchInterfaceControllerLook CONTROLLERLOOK = new NotchInterfaceControllerLook();
	public static final NotchInterfaceEntity ENTITY = new NotchInterfaceEntity();
	public static final CBInterfaceJavaPluginLoader JAVAPLUGINLOADER = new CBInterfaceJavaPluginLoader();
	public static final NotchInterfaceNavigation NAVIGATION = new NotchInterfaceNavigation();
	public static final NotchInterfacePathfinderGoal PATHFINDERGOAL = new NotchInterfacePathfinderGoal();
	public static final NotchInterfacePathfinderGoalSelector PATHFINDERGOALSELECTOR = new NotchInterfacePathfinderGoalSelector();
	public static final NotchInterfacePathfinderGoalSelectorItem PATHFINDERGOALSELECTORITEM = new NotchInterfacePathfinderGoalSelectorItem();
	public static final CBInterfacePluginClassLoader PLUGINCLASSLOADER = new CBInterfacePluginClassLoader();
	public static final NotchInterfaceWorld WORLD = new NotchInterfaceWorld();
	public static final NmsIAttribute IATTRIBUTE = new NmsIAttribute();
	public static final NmsAttributeRanged ATTRIBUTERANGED = new NmsAttributeRanged();
	public static final NmsAttributeModifiable ATTRIBUTEMODIFIABLE = new NmsAttributeModifiable();
	public static final NmsAttributeModifier ATTRIBUTEMODIFIER = new NmsAttributeModifier();
	public static final NmsGenericAttributes GENERICATTRIBUTES = new NmsGenericAttributes();
	public static final NmsEntityInsentient ENTITYINSENTIENT = new NmsEntityInsentient();
	
	private NativeInterfaces() {
		throw new AssertionError();
	}

}
