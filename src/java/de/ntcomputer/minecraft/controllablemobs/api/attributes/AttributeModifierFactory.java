package de.ntcomputer.minecraft.controllablemobs.api.attributes;

import de.ntcomputer.minecraft.controllablemobs.implementation.attributes.CraftAttributeModifier;

public final class AttributeModifierFactory {

	private AttributeModifierFactory() {
		throw new AssertionError();
	}
	
	
	public static final AttributeModifier create(String name, double modifierValue, ModifyOperation operation) {
		return new CraftAttributeModifier(name, modifierValue, operation);
	}

}
