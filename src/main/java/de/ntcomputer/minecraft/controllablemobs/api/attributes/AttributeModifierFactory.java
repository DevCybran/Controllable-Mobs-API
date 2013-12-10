package de.ntcomputer.minecraft.controllablemobs.api.attributes;

import java.util.UUID;

import de.ntcomputer.minecraft.controllablemobs.implementation.attributes.CraftAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.attributes.CraftAttributeModifier;

/**
 * This class lets you create instances of {@link AttributeModifier}
 * 
 * @author Cybran
 * @version v4
 *
 */
public final class AttributeModifierFactory {

	private AttributeModifierFactory() {
		throw new AssertionError();
	}
	
	/**
	 * Creates an attribute modifier. You should create as few modifiers as possible and store them as static final!
	 * One modifier can then be attached to multiple attributes.
	 * 
	 * @param uniqueID an UUID that should be the same when creating the same modifier. Means: you should hardcode this UUID into your plugin, like this: UUID.fromString("8971a510-ec88-11e2-91e2-0800200c9a66")
	 * @param name a name for you modifier. Example: "Rage speed boost"
	 * @param modifierValue the value an attribute should be modified with
	 * @param operation an operation describing in which way the modifier value affects the attribute
	 * @return the created attribute modifier object
	 */
	public static final AttributeModifier create(UUID uniqueID, String name, double modifierValue, ModifyOperation operation) {
		CraftAttributeModifier modifier = new CraftAttributeModifier(uniqueID, name, modifierValue, operation);
		CraftAttribute.registerCustomModifier(modifier);
		return modifier;
	}

}
