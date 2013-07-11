package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public interface Attribute<T> {

	public T getValue();
	public void setBasisValue(T value);
	
}
