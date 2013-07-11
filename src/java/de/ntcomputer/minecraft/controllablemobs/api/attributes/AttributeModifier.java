package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public interface AttributeModifier<T> {

	public String getName();
	public ModifyOperation getOperation();
	public T getModifierValue();
	
	public net.minecraft.server.v1_6_R2.AttributeModifier getNativeAttributeModifier();
	
}
