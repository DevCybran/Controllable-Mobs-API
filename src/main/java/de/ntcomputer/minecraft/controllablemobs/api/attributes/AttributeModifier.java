package de.ntcomputer.minecraft.controllablemobs.api.attributes;

import java.util.UUID;

/**
 * An interface representing a modifier which can be attached to any attribute.
 * Modifiers are changing the basis value of an attribute in a way determinated by the modifier operation and modifier value.
 * Modifiers are being identified by a UUID. This UUID should be unique and non-random.
 * Modifiers should be stored static and final. One Modifier can be attached to multiple attributes.
 * Use {@link AttributeModifierFactory#create(UUID, String, double, ModifyOperation)} to retrieve an instance.
 *  
 * @author Cybran
 * @version v4
 *
 */
public interface AttributeModifier {

	/**
	 * @return the modifier's UUID
	 */
	public UUID getUniqueID();
	
	/**
	 * @return the modifier's name
	 */
	public String getName();
	
	/**
	 * @return the modifier's modify operation
	 */
	public ModifyOperation getOperation();
	
	/**
	 * @return the modifier's modify value
	 */
	public double getModifierValue();

	/**
	 * @return An array of all attributes this modifier is currently attached to. May be incomplete if the provided UUID is not static.
	 */
	public Attribute[] getAttachedAttributes();
	
	/**
	 * Unattaches this modifier from all attributes it is currently attached to.
	 */
	public void unattachAttributes();
	
	/**
	 * @return whether this modifier has been created by a Plugin author (true), or by the native minecraft server (false)
	 */
	public boolean isCustomModifier();

}