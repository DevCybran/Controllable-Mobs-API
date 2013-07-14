package de.ntcomputer.minecraft.controllablemobs.api.attributes;

import java.util.UUID;


public interface AttributeModifier {

	public UUID getUniqueID();
	public String getName();
	public ModifyOperation getOperation();
	public double getModifierValue();

	public Attribute[] getAttachedAttributes();
	public void unattachAttributes();
	
	public boolean isCustomModifier();

}