package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public interface ModifiableAttribute<T> extends Attribute<T> {

	public T getValue();
	
	public AttributeModifier<T> createModifier(T modifierValue, ModifyOperation operation, boolean attachInstantly);
	public AttributeModifier<T>[] getAttachedModifiers();
	public void unattachModifiers();
	
}
