package de.ntcomputer.minecraft.controllablemobs.api.attributes;

/**
 * An interface representing an attribute of a controllable mob.
 * Use one of the various methods located at {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMobAttributes} to retrieve an attribute instance.
 * 
 * @author Cybran
 * @version v4
 *
 */
public interface Attribute {
	
	/**
	 * @return the name of this attribute
	 */
	public String getAttributeName();
	
	/**
	 * @return the value of this attribute before any modifiers are applied.
	 */
	public double getBasisValue();
	
	/**
	 * Sets the attribute's basic value.
	 * @param value the raw attribute value
	 */
	public void setBasisValue(double value);
	
	/**
	 * Resets the basis value to the value it had before the entity was put under your control.
	 * @return the restored value
	 */
	public double resetBasisValue();

	/**
	 * @return the value of this attribute after all modifiers have been applied. The value will never be lower than the minimum value or higher than the maximum value 
	 */
	public double getValue();
	
	/**
	 * @return the lowest value allowed for this attribute
	 */
	public double getMinimum();
	
	/**
	 * @return the highest value allowed for this attribute
	 */
	public double getMaximum();
	
	/**
	 * Checks whether a certain modifier is attached to this attribute.
	 * @param modifier the modifier to check for
	 * @return true if attached, false otherwise
	 */
	public boolean hasModifierAttached(AttributeModifier modifier);
	
	/**
	 * Attaches a custom modifier to this attribute.
	 * @param modifier the modifier which shall be attached
	 * @return true when the modifier has been successfully attached, false if the modifier has already been attached before
	 */
	public boolean attachModifier(AttributeModifier modifier);
	
	/**
	 * Unattaches a custom modifier from this attribute.
	 * @param modifier the modifier which shall be unattached.
	 */
	public void unattachModifier(AttributeModifier modifier);
	
	/**
	 * @return all currently attached modifiers, including native and custom ones.
	 */
	public AttributeModifier[] getAttachedModifiers();
	
	/**
	 * @param operation an operation filter
	 * @return all currently attached modifiers of the provided operation
	 */
	public AttributeModifier[] getAttachedModifiers(ModifyOperation operation);
	
	/**
	 * Unattaches all modifiers, including native and custom ones.
	 */
	public void unattachModifiers();
	
	/**
	 * Unattaches all custom modifiers.
	 */
	public void unattachCustomModifiers();
	
	/**
	 * Unattaches all native modifiers.
	 */
	public void unattachNativeModifiers();
	
	/**
	 * Unattaches all custom modifiers and resets the basis value.
	 * @see Attribute#unattachCustomModifiers()
	 * @see Attribute#resetBasisValue()
	 */
	public void resetAttribute();
	
}
