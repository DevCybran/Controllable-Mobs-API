package de.ntcomputer.minecraft.controllablemobs.api.attributes;

import java.util.UUID;

import de.ntcomputer.minecraft.controllablemobs.implementation.attributes.CraftAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.attributes.CraftAttributeModifier;

public final class AttributeModifierFactory {

	private AttributeModifierFactory() {
		throw new AssertionError();
	}
	
	
	public static final AttributeModifier create(UUID uniqueID, String name, double modifierValue, ModifyOperation operation) {
		CraftAttributeModifier modifier = new CraftAttributeModifier(uniqueID, name, modifierValue, operation);
		CraftAttribute.registerCustomModifier(modifier);
		return modifier;
	}

}
