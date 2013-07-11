package de.ntcomputer.minecraft.controllablemobs.implementation.attributes;

import net.minecraft.server.v1_6_R2.AttributeModifiable;
import net.minecraft.server.v1_6_R2.AttributeRanged;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.AttributeModifier;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.DoubleAttribute;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.ModifyOperation;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NmsInterfaces;

public final class NativeAttribute implements DoubleAttribute {
	private final AttributeModifiable nativeAttribute;
	private final AttributeRanged nativeAttributeTemplate;

	public NativeAttribute(AttributeModifiable nativeAttribute) {
		this.nativeAttribute = nativeAttribute;
		this.nativeAttributeTemplate = (AttributeRanged) NmsInterfaces.ATTRIBUTEMODIFIABLE.METHOD_GETATTRIBUTETEMPLATE.invoke(nativeAttribute);
	}

	@Override
	public Double getValue() {
		return nativeAttribute.getValue();
	}

	@Override
	public AttributeModifier<Double>[] getAttachedModifiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unattachModifiers() {
		// TODO Auto-generated method stub

	}

	@Override
	public Double getBasisValue() {
		return NmsInterfaces.ATTRIBUTEMODIFIABLE.METHOD_GETBASISVALUE.invoke(this.nativeAttribute);
	}

	@Override
	public void setBasisValue(Double value) {
		this.nativeAttribute.setValue(value);
	}

	@Override
	public Double getMinimum() {
		return NmsInterfaces.ATTRIBUTERANGED.FIELD_MINIMUM.get(this.nativeAttributeTemplate);
	}

	@Override
	public Double getMaximum() {
		return NmsInterfaces.ATTRIBUTERANGED.FIELD_MAXIMUM.get(this.nativeAttributeTemplate);
	}

	@Override
	public String getAttributeName() {
		return NmsInterfaces.IATTRIBUTE.METHOD_GETNAME.invoke(this.nativeAttributeTemplate);
	}

	@Override
	public boolean attachModifier(AttributeModifier<Double> modifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unattachModifier(AttributeModifier<Double> modifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AttributeModifier<Double>[] getAttachedModifiers(ModifyOperation operation) {
		// TODO Auto-generated method stub
		return null;
	}

}
