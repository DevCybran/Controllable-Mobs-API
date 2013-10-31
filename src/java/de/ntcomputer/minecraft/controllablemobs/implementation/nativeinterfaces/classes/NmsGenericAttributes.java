package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import net.minecraft.server.v1_6_R3.GenericAttributes;
import net.minecraft.server.v1_6_R3.IAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeStaticFieldObject;

public final class NmsGenericAttributes {
	// look at the string description in GenericAttributes class on update
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_MOVEMENTSPEED = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"d");
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_ATTACKDAMAGE = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"e");
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_MAXHEALTH = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"a");
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_KNOCKBACKRESISTANCE = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"c");
	public final NativeStaticFieldObject<GenericAttributes,IAttribute> STATIC_FIELD_FOLLOWRANGE = new NativeStaticFieldObject<GenericAttributes,IAttribute>(GenericAttributes.class,"b");
	
	
	
}
