package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_7_R4.GenericAttributes;
import net.minecraft.server.v1_7_R4.IAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeStaticFieldObject;

public final class NmsGenericAttributes {
	// look at the string description in GenericAttributes class on update
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_MAXHEALTH = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"maxHealth");
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_FOLLOWRANGE = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"b");
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_KNOCKBACKRESISTANCE = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"c");
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_MOVEMENTSPEED = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"d");
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_ATTACKDAMAGE = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"e");
	
}
