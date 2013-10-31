package de.ntcomputer.minecraft.controllablemobs.implementation.attributes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import de.ntcomputer.minecraft.controllablemobs.api.attributes.Attribute;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.AttributeModifier;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.ModifyOperation;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;


public class CraftAttributeModifier implements AttributeModifier {
	private final UUID uniqueID; 
	private final String name;
	private final double value;
	private final ModifyOperation operation;
	private net.minecraft.server.v1_6_R3.AttributeModifier nativeModifier = null;
	private final Set<CraftAttribute> attachedAttributes = new HashSet<CraftAttribute>();
	private final boolean custom;
	
	public CraftAttributeModifier(UUID uniqueID, String name, double modifierValue, ModifyOperation operation) {
		this.uniqueID = uniqueID;
		this.name = name;
		this.value = modifierValue;
		this.operation = operation;
		this.custom = true;
	}
	
	public CraftAttributeModifier(UUID uuid, net.minecraft.server.v1_6_R3.AttributeModifier nativeModifier) {
		this.uniqueID = uuid;
		this.nativeModifier = nativeModifier;
		this.name = NativeInterfaces.ATTRIBUTEMODIFIER.METHOD_GETNAME.invoke(nativeModifier);
		this.value = NativeInterfaces.ATTRIBUTEMODIFIER.METHOD_GETAMOUNT.invoke(nativeModifier);
		this.operation = ModifyOperation.byIntType(NativeInterfaces.ATTRIBUTEMODIFIER.METHOD_GETOPERATION.invoke(nativeModifier));
		this.custom = false;
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
	
	net.minecraft.server.v1_6_R3.AttributeModifier getNativeModifier() {
		if(this.nativeModifier==null) {
			this.nativeModifier = new net.minecraft.server.v1_6_R3.AttributeModifier(uniqueID, name, value, this.operation.getIntType());
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

	@Override
	public UUID getUniqueID() {
		return this.uniqueID;
	}

	@Override
	public boolean isCustomModifier() {
		return this.custom;
	}
	
}
