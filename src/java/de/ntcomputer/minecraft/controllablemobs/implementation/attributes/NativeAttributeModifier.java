package de.ntcomputer.minecraft.controllablemobs.implementation.attributes;

import de.ntcomputer.minecraft.controllablemobs.api.attributes.AttributeModifier;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.ModifyOperation;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NmsInterfaces;

public final class NativeAttributeModifier implements AttributeModifier<Double> {
	private final net.minecraft.server.v1_6_R2.AttributeModifier nativeAttributeModifier;
	private final ModifyOperation operation;

	public NativeAttributeModifier(net.minecraft.server.v1_6_R2.AttributeModifier nativeAttributeModifier) {
		this.nativeAttributeModifier = nativeAttributeModifier;
		this.operation = ModifyOperation.byIntType(NmsInterfaces.ATTRIBUTEMODIFIER.METHOD_GETOPERATION.invoke(nativeAttributeModifier));
	}

	@Override
	public String getName() {
		return NmsInterfaces.ATTRIBUTEMODIFIER.METHOD_GETNAME.invoke(this.nativeAttributeModifier);
	}

	@Override
	public ModifyOperation getOperation() {
		return this.operation;
	}

	@Override
	public Double getModifierValue() {
		return NmsInterfaces.ATTRIBUTEMODIFIER.METHOD_GETAMOUNT.invoke(this.nativeAttributeModifier);
	}

	@Override
	public net.minecraft.server.v1_6_R2.AttributeModifier getNativeAttributeModifier() {
		return this.nativeAttributeModifier;
	}

}
