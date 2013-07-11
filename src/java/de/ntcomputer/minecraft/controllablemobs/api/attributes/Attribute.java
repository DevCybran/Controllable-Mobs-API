package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public interface Attribute<T> {

	public String getAttributeName();
	
	public T getBasisValue();
	public void setBasisValue(T value);
	
}
