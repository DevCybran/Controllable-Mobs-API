package de.ntcomputer.minecraft.controllablemobs.api.attributes;

public class CustomAttributeModifier<T> implements AttributeModifier<T> {
	private final String name;
	private final T value;
	private final ModifyOperation operation;
	
	public CustomAttributeModifier(String name, T modifierValue, ModifyOperation operation) {
		this.name = name;
		this.value = modifierValue;
		this.operation = operation;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public ModifyOperation getOperation() {
		return this.operation;
	}
	
	@Override
	public T getModifierValue() {
		return this.value;
	}
	
}
