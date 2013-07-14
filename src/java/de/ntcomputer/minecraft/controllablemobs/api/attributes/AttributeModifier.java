package de.ntcomputer.minecraft.controllablemobs.api.attributes;


public interface AttributeModifier {

	public String getName();
	public ModifyOperation getOperation();
	public double getModifierValue();

	public Attribute[] getAttachedAttributes();
	public void unattachAttributes();

}