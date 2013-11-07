package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.CBInterfaceJavaPluginLoader;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.CBInterfacePluginClassLoader;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsAttributeModifiable;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsAttributeModifier;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsAttributeRanged;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsEntityInsentient;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsGenericAttributes;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsIAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsControllerJump;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsControllerLook;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsEntity;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsNavigation;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsInterfacePathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsPathfinderGoalSelector;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsPathfinderGoalSelectorItem;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server.NmsWorld;

public final class NativeInterfaces {
	public static final NmsControllerJump CONTROLLERJUMP = new NmsControllerJump();
	public static final NmsControllerLook CONTROLLERLOOK = new NmsControllerLook();
	public static final NmsEntity ENTITY = new NmsEntity();
	public static final CBInterfaceJavaPluginLoader JAVAPLUGINLOADER = new CBInterfaceJavaPluginLoader();
	public static final NmsNavigation NAVIGATION = new NmsNavigation();
	public static final NmsInterfacePathfinderGoal PATHFINDERGOAL = new NmsInterfacePathfinderGoal();
	public static final NmsPathfinderGoalSelector PATHFINDERGOALSELECTOR = new NmsPathfinderGoalSelector();
	public static final NmsPathfinderGoalSelectorItem PATHFINDERGOALSELECTORITEM = new NmsPathfinderGoalSelectorItem();
	public static final CBInterfacePluginClassLoader PLUGINCLASSLOADER = new CBInterfacePluginClassLoader();
	public static final NmsWorld WORLD = new NmsWorld();
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
