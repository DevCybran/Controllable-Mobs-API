package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public interface AttributeModifier<T> {

	public ModifiableAttribute<T> getAttribute();
	public String getName();
	public T getModifierValue();
	
	public boolean attach();
	public boolean unattach();

	
}
