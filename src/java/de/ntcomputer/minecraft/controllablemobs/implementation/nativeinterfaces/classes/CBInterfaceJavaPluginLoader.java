package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPluginLoader;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;

public final class CBInterfaceJavaPluginLoader {
	public NativeFieldObject<JavaPluginLoader,Server> FIELD_SERVER = new NativeFieldObject<JavaPluginLoader,Server>(JavaPluginLoader.class, "server");

}
