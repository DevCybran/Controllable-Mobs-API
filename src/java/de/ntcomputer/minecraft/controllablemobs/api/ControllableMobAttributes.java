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
	
	/**
	 * @return the movement speed attribute
	 */
	public Attribute getMovementSpeedAttribute();
	/**
	 * @return the attack damage attribute. Caution: Will return null if the entity is not an attacking entity
	 */
	public Attribute getAttackDamageAttribute();
	/**
	 * @return the maximum health attribute. Caution: When changing the maximum health, the entity's current health is not scaled or updated
	 */
	public Attribute getMaxHealthAttribute();
	/**
	 * @return a knockback resistance attribute. Ranged from 0.0 to 1.0, where 1.0 is full resistance (entity will be never knocked back when attacked)
	 */
	public Attribute getKnockbackResistanceAttribute();
	
	/**
	 * Defines a maximum distance for entity navigation. If any target of the entity is further away, the entity will not move.
	 * Impacts all aspects of entity movement, whether invoked by {@link ControllableMobActions#moveTo(org.bukkit.Location)}, {@link ControllableMobActions#follow(org.bukkit.entity.LivingEntity)} or {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIAttackMelee} (etc.) is not important.
	 * <b>Warning: High values or infinite can be very costly (severe CPU load)! To reduce this effect, limit the distance.</b>
	 * 
	 * @param distance the new maximum distance for entity navigation (in blocks). The default value is 200 blocks. Ranged from 16 to 2048. Specify 0 to set unlimited.
	 * @return true, if the distance was in a valid range.
	 */
	public boolean setMaximumNavigationDistance(double distance);
	
	/**
	 * Gets the maximum distance for entity navigation.
	 * @return the current maximum distance for entity navigation (in blocks). The default value is 200 blocks.
	 */
	public double getMaximumNavigationDistance();
	
}
