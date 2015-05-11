package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_7_R4.AttributeRanged;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldDouble;

public final class NmsAttributeRanged {
	public final NativeFieldDouble<AttributeRanged> FIELD_MINIMUM = new NativeFieldDouble<AttributeRanged>(AttributeRanged.class, "a");
	public final NativeFieldDouble<AttributeRanged> FIELD_MAXIMUM = new NativeFieldDouble<AttributeRanged>(AttributeRanged.class, "b");

}
