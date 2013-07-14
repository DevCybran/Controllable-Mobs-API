package de.ntcomputer.minecraft.controllablemobs.implementation.attributes;

import java.util.HashSet;
import java.util.Set;

import de.ntcomputer.minecraft.controllablemobs.api.attributes.Attribute;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.AttributeModifier;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.ModifyOperation;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;


public class CraftAttributeModifier implements AttributeModifier {
	private final String name;
	private final double value;
	private final ModifyOperation operation;
	private net.minecraft.server.v1_6_R2.AttributeModifier nativeModifier = null;
	private final Set<CraftAttribute> attachedAttributes = new HashSet<CraftAttribute>();
	
	public CraftAttributeModifier(String name, double modifierValue, ModifyOperation operation) {
		this.name = name;
		this.value = modifierValue;
		this.operation = operation;
	}
	
	public CraftAttributeModifier(net.minecraft.server.v1_6_R2.AttributeModifier nativeModifier) {
		this.nativeModifier = nativeModifier;
		this.name = NativeInterfaces.ATTRIBUTEMODIFIER.METHOD_GETNAME.invoke(nativeModifier);
		this.value = NativeInterfaces.ATTRIBUTEMODIFIER.METHOD_GETAMOUNT.invoke(nativeModifier);
		this.operation = ModifyOperation.byIntType(NativeInterfaces.ATTRIBUTEMODIFIER.METHOD_GETOPERATION.invoke(nativeModifier));
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
	public double getModifierValue() {
		return this.value;
	}
	
	net.minecraft.server.v1_6_R2.AttributeModifier getNativeModifier() {
		if(this.nativeModifier==null) {
			// TODO: create native modifier from custom data
		}
		return this.nativeModifier;
	}
	
	@Override
	public Attribute[] getAttachedAttributes() {
		return this.attachedAttributes.toArray(new Attribute[this.attachedAttributes.size()]);
	}
	
	@Override
	public void unattachAttributes() {
		for(CraftAttribute attribute: this.attachedAttributes) {
			attribute.unattachModifierWithoutUpdate(this);
		}
		this.attachedAttributes.clear();
	}
	
	void setAttributeAttached(CraftAttribute attribute) {
		this.attachedAttributes.add(attribute);
	}
	
	void setAttributeUnattached(CraftAttribute attribute) {
		this.attachedAttributes.remove(attribute);
	}
	
}
