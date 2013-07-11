package de.ntcomputer.minecraft.controllablemobs.api;

import de.ntcomputer.minecraft.controllablemobs.api.attributes.DoubleAttribute;

/**
 * An interface to control properties of an entity. Only movement speed is implemented yet
 * You can retrieve an instance by using {@link ControllableMob#getProperties()}
 * 
 * @author Cybran
 * @version v4
 */
public interface ControllableMobAttributes {
	
	public DoubleAttribute getMovementSpeedAttribute();
	
}
