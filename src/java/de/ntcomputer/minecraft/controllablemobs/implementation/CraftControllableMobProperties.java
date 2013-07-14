package de.ntcomputer.minecraft.controllablemobs.implementation;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobProperties;

@Deprecated
public class CraftControllableMobProperties implements ControllableMobProperties {
	private CraftControllableMobAttributes attributes;
		
	CraftControllableMobProperties(CraftControllableMobAttributes attributes) {
		this.attributes = attributes;
	}

	@Override
	public void setMovementSpeed(float movementSpeed) {
		this.attributes.getMovementSpeedAttribute().setBasisValue(movementSpeed);
	}

	@Override
	public float getMovementSpeed() {
		return (float) this.attributes.getMovementSpeedAttribute().getValue();
	}

	@Override
	public void setMaximumWalkingDistance(float distance) throws IllegalArgumentException {
		this.attributes.setMaximumNavigationDistance(distance);
	}

	@Override
	public float getMaximumWalkingDistance() {
		return (float) this.attributes.getMaximumNavigationDistance();
	}

	void dispose() {
		this.attributes = null;
	}

}
