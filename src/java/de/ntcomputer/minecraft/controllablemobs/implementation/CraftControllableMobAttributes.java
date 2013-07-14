package de.ntcomputer.minecraft.controllablemobs.implementation;

import net.minecraft.server.v1_6_R2.AttributeModifiable;
import net.minecraft.server.v1_6_R2.EntityInsentient;
import net.minecraft.server.v1_6_R2.IAttribute;
import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobAttributes;
import de.ntcomputer.minecraft.controllablemobs.api.attributes.Attribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.attributes.CraftAttribute;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public final class CraftControllableMobAttributes implements ControllableMobAttributes {
	private EntityInsentient nmsEntity;
	private CraftAttribute movementSpeed;
	private CraftAttribute attackDamage;
	private CraftAttribute maxHealth;
	private CraftAttribute knockbackResistance;
	private CraftAttribute followRange;
	private double maximumNavigationDistance = 200.0D;

	public CraftControllableMobAttributes(CraftControllableMob<?> mob) {
		this.nmsEntity = mob.notchEntity;
		this.movementSpeed = new CraftAttribute(this.getAttributeInstance(NativeInterfaces.GENERICATTRIBUTES.STATIC_FIELD_MOVEMENTSPEED.get()));
		this.attackDamage = new CraftAttribute(this.getAttributeInstance(NativeInterfaces.GENERICATTRIBUTES.STATIC_FIELD_ATTACKDAMAGE.get()));
		this.maxHealth = new CraftAttribute(this.getAttributeInstance(NativeInterfaces.GENERICATTRIBUTES.STATIC_FIELD_MAXHEALTH.get()));
		this.knockbackResistance = new CraftAttribute(this.getAttributeInstance(NativeInterfaces.GENERICATTRIBUTES.STATIC_FIELD_KNOCKBACKRESISTANCE.get()));
		this.followRange = new CraftAttribute(this.getAttributeInstance(NativeInterfaces.GENERICATTRIBUTES.STATIC_FIELD_FOLLOWRANGE.get()));
	}
	
	private AttributeModifiable getAttributeInstance(IAttribute attrib) {
		return (AttributeModifiable) this.nmsEntity.getAttributeInstance(attrib);
	}

	@Override
	public Attribute getMovementSpeedAttribute() {
		return this.movementSpeed;
	}

	@Override
	public Attribute getAttackDamageAttribute() {
		return this.attackDamage;
	}

	@Override
	public Attribute getMaxHealthAttribute() {
		return this.maxHealth;
	}

	@Override
	public Attribute getKnockbackResistanceAttribute() {
		return this.knockbackResistance;
	}

	@Override
	public void setMaximumNavigationDistance(double distance) throws IllegalArgumentException {
		if(distance!=0 && distance<16) throw new IllegalArgumentException("distance has to be greater than or equal to 16 blocks");
		this.maximumNavigationDistance = distance;
		if(distance!=0 && this.followRange.getBasisValue() > distance) {
			this.followRange.setBasisValue(distance);
		}
	}

	@Override
	public double getMaximumNavigationDistance() {
		return this.maximumNavigationDistance;
	}
	
	public void adjustMaximumNavigationDistance(double forDistance) {
		if(this.maximumNavigationDistance==0 || this.maximumNavigationDistance>=forDistance) {
			this.followRange.setBasisValue(forDistance);
		}
	}
	
	void dispose(boolean reset) {
		this.attackDamage.dispose(reset);
		this.maxHealth.dispose(reset);
		this.followRange.dispose(reset);
		this.knockbackResistance.dispose(reset);
		this.movementSpeed.dispose(reset);
		this.attackDamage = null;
		this.maxHealth = null;
		this.followRange = null;
		this.knockbackResistance = null;
		this.movementSpeed = null;
		this.nmsEntity = null;
	}

}
