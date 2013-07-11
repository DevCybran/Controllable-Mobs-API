package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public interface ModifiableAttribute<T> extends Attribute<T> {

	public T getValue();
	public T getMinimum();
	public T getMaximum();
	
	public boolean attachModifier(AttributeModifier<T> modifier);
	public boolean unattachModifier(AttributeModifier<T> modifier);
	public AttributeModifier<T>[] getAttachedModifiers();
	public AttributeModifier<T>[] getAttachedModifiers(ModifyOperation operation);
	public void unattachModifiers();
	
}
