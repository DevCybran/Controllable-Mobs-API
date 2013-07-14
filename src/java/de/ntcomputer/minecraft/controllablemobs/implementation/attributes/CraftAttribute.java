package de.ntcomputer.minecraft.controllablemobs.implementation.attributes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_6_R2.AttributeModifiable;
import net.minecraft.server.v1_6_R2.AttributeRanged;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.Attribute;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.AttributeModifier;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.ModifyOperation;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public final class CraftAttribute implements Attribute {
	private static final Map<net.minecraft.server.v1_6_R2.AttributeModifier,CraftAttributeModifier> nativeModifierMap = new HashMap<net.minecraft.server.v1_6_R2.AttributeModifier,CraftAttributeModifier>();
	private final AttributeModifiable nativeAttribute;
	private final AttributeRanged nativeAttributeTemplate;

	public CraftAttribute(AttributeModifiable nativeAttribute) {
		this.nativeAttribute = nativeAttribute;
		this.nativeAttributeTemplate = (AttributeRanged) NativeInterfaces.ATTRIBUTEMODIFIABLE.METHOD_GETATTRIBUTETEMPLATE.invoke(nativeAttribute);
	}

	@Override
	public double getValue() {
		return nativeAttribute.getValue();
	}
	
	private CraftAttributeModifier[] resolveNativeModifiers(Collection<net.minecraft.server.v1_6_R2.AttributeModifier> nativeModifiers) {
		CraftAttributeModifier[] result = new CraftAttributeModifier[nativeModifiers.size()];
		int i = 0;
		for(net.minecraft.server.v1_6_R2.AttributeModifier nativeModifier: nativeModifiers) {
			CraftAttributeModifier modifier = nativeModifierMap.get(nativeModifier);
			if(modifier==null) {
				modifier = new CraftAttributeModifier(nativeModifier);
				nativeModifierMap.put(nativeModifier, modifier);
			}
			result[i] = modifier;
			i++;
		}
		return result;
	}

	@Override
	public CraftAttributeModifier[] getAttachedModifiers() {
		return this.resolveNativeModifiers(NativeInterfaces.ATTRIBUTEMODIFIABLE.METHOD_GETMODIFIERS.invoke(nativeAttribute));
	}

	@Override
	public void unattachModifiers() {
		CraftAttributeModifier[] modifiers = this.getAttachedModifiers();
		for(CraftAttributeModifier modifier: modifiers) {
			NativeInterfaces.ATTRIBUTEMODIFIABLE.METHOD_REMOVEMODIFIER.invoke(nativeAttribute, modifier.getNativeModifier());
			modifier.setAttributeUnattached(this);
		}
	}

	@Override
	public double getBasisValue() {
		return NativeInterfaces.ATTRIBUTEMODIFIABLE.METHOD_GETBASISVALUE.invoke(this.nativeAttribute);
	}

	@Override
	public void setBasisValue(double value) {
		this.nativeAttribute.setValue(value);
	}

	@Override
	public double getMinimum() {
		return NativeInterfaces.ATTRIBUTERANGED.FIELD_MINIMUM.get(this.nativeAttributeTemplate);
	}

	@Override
	public double getMaximum() {
		return NativeInterfaces.ATTRIBUTERANGED.FIELD_MAXIMUM.get(this.nativeAttributeTemplate);
	}

	@Override
	public String getAttributeName() {
		return NativeInterfaces.IATTRIBUTE.METHOD_GETNAME.invoke(this.nativeAttributeTemplate);
	}

	@Override
	public boolean attachModifier(AttributeModifier modifier) {
		try {
			NativeInterfaces.ATTRIBUTEMODIFIABLE.METHOD_ADDMODIFIER.invoke(nativeAttribute, ((CraftAttributeModifier) modifier).getNativeModifier());
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public void unattachModifier(AttributeModifier modifier) {
		this.unattachModifierWithoutUpdate((CraftAttributeModifier) modifier);
		((CraftAttributeModifier) modifier).setAttributeUnattached(this);
	}
	
	void unattachModifierWithoutUpdate(CraftAttributeModifier modifier) {
		NativeInterfaces.ATTRIBUTEMODIFIABLE.METHOD_REMOVEMODIFIER.invoke(nativeAttribute, modifier.getNativeModifier());
	}

	@Override
	public AttributeModifier[] getAttachedModifiers(ModifyOperation operation) {
		return this.resolveNativeModifiers(NativeInterfaces.ATTRIBUTEMODIFIABLE.METHOD_GETMODIFIERSBYTYPE.invoke(this.nativeAttribute, operation.getIntType()));
	}

	@Override
	public double resetBasisValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unattachCustomModifiers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unattachNativeModifiers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetAttribute() {
		// TODO Auto-generated method stub
		
	}
	
	public void dispose(boolean reset) {
		
	}

}
