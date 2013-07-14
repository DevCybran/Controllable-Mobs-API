package de.ntcomputer.minecraft.controllablemobs.api.attributes;


public interface Attribute {
	
	public String getAttributeName();
	
	public double getBasisValue();
	public void setBasisValue(double value);
	public double resetBasisValue();

	public double getValue();
	public double getMinimum();
	public double getMaximum();
	
	public boolean attachModifier(AttributeModifier modifier);
	public void unattachModifier(AttributeModifier modifier);
	public AttributeModifier[] getAttachedModifiers();
	public AttributeModifier[] getAttachedModifiers(ModifyOperation operation);
	public void unattachModifiers();
	public void unattachCustomModifiers();
	public void unattachNativeModifiers();
	
	public void resetAttribute();
	
}
