package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public interface Attribute<T> {

	public T getBasisValue();
	public void setBasisValue(T value);
	
}
