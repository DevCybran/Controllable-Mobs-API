package de.ntcomputer.minecraft.controllablemobs.api;

import de.ntcomputer.minecraft.controllablemobs.api.attributes.Attribute;

/**
 * An interface to control attributes of an entity.
 * You can retrieve an instance by using {@link ControllableMob#getAttributes()}
 * 
 * @author Cybran
 * @version v4
 */
public interface ControllableMobAttributes {
	
	public Attribute getMovementSpeedAttribute();
	public Attribute getAttackDamageAttribute();
	public Attribute getMaxHealthAttribute();
	public Attribute getKnockbackResistanceAttribute();
	
	/**
	 * Defines a maximum distance for entity navigation. If any target of the entity is further away, the entity will not move.
	 * Impacts all aspects of entity movement, whether invoked by {@link ControllableMobActions#moveTo(org.bukkit.Location)}, {@link ControllableMobActions#follow(org.bukkit.entity.LivingEntity)} or {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIAttackMelee} (etc.) is not important.
	 * <b>Warning: High values or infinite can be very costly (severe CPU load)! To reduce this effect, limit the distance.</b>
	 * 
	 * @param distance the new maximum distance for entity navigation (in blocks). The default value is 200 blocks.
	 * @throws IllegalArgumentException when distance is negative or less than 16 blocks.
	 */
	public void setMaximumNavigationDistance(double distance) throws IllegalArgumentException;
	
	/**
	 * Gets the maximum distance for entity navigation.
	 * @return the current maximum distance for entity navigation (in blocks). The default value is 200 blocks.
	 */
	public double getMaximumNavigationDistance();
	
}
