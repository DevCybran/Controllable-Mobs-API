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
	 * The default movement speed is 0.7. Default for monsters is 0.25. You should not modify the speed to a value higher than 2.0, or the entity's movement will become unpredictable. 
	 * @return the movement speed attribute.
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
	
	
	/**
	 * Determines whether the entity can swim. To change this value, add or remove {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AISwim} to {@link ControllableMobAI}
	 * @return whether the entity can swim.
	 */
	public boolean canSwim();
	
	/**
	 * @return whether the entity avoids moving through water (regardless of whether it can swim).
	 */
	public boolean getAvoidWater();
	
	/**
	 * Set whether the entity avoids moving through water (regardless of whether it can swim).
	 * @param avoid true, if the entity should not move through water
	 */
	public void setAvoidWater(boolean avoid);
	
	
	/**
	 * Get whether the entity will move through doors to reach its destination.
	 * This only shows whether the entity will move through opened doors.
	 * It does not show whether the entity will move through closed doors. Use {@link #canMoveThroughClosedDoors()} for this case.
	 * @return whether the entity will move through doors to reach its destination.
	 */
	public boolean getMoveThroughDoors();
	
	/**
	 * Set whether the entity will move through doors to reach its destination.
	 * If set to true, the entity will move through opened doors. For closed doors, see {@link #canMoveThroughClosedDoors()}
	 * If set to false, the entity will not move through any closed or opened door, regardless of AI behaviors.
	 * @param moveThroughDoors whether the entity should move through doors to reach its destination.
	 */
	public void setMoveThroughDoors(boolean moveThroughDoors);
	
	/**
	 * Get whether the entity can move through closed doors to reach its destination.
	 * To adjust this behavior, add or remove either {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIDoorOpen} or {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIDoorBreak} to {@link ControllableMobAI}.
	 * @return whether the entity can somehow move through closed doors to reach its destination.
	 */
	public boolean canMoveThroughClosedDoors();
	
}
