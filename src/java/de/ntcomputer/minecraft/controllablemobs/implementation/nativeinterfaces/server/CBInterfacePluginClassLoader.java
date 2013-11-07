package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.java.PluginClassLoader;

import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;

public final class CBInterfacePluginClassLoader {
	public NativeFieldObject<PluginClassLoader,JavaPluginLoader> FIELD_LOADER = new NativeFieldObject<PluginClassLoader,JavaPluginLoader>(PluginClassLoader.class, "loader");

}
