package de.ntcomputer.minecraft.controllablemobs.api;

/**
 * An interface to control properties of an entity. Only movement speed is implemented yet
 * You can retrieve an instance by using {@link ControllableMob#getProperties()}
 * 
 * @author Cybran
 * @version v4
 * @deprecated use {@link ControllableMobAttributes} instead!
 */
@Deprecated
public interface ControllableMobProperties {
	
	/**
	 * Changes the entities movement speed.
	 * 
	 * @param movementSpeed This float value has to be in a range between 0.01 and 2.0, or it will be ignored. The default value is 0.25 for monsters. <i>High values might cause buggy animations and a worse pathfinding.</i>
	 */
	public void setMovementSpeed(float movementSpeed);
	
	
	/**
	 * Gets the entities movement speed.
	 * 
	 * @return the current movement speed. The default value is 0.25 for monsters.
	 */
	public float getMovementSpeed();
	
	/**
	 * Defines a maximum distance for entity navigation. If any target of the entity is further away, the entity will not move.
	 * Impacts all aspects of entity movement, whether invoked by {@link ControllableMobActions#moveTo(org.bukkit.Location)}, {@link ControllableMobActions#follow(org.bukkit.entity.LivingEntity)} or {@link de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIAttackMelee} (etc.) is not important.
	 * <b>Warning: High values or infinite can be very costly (severe CPU time usage)! To reduce this effect, limit the distance.</b>
	 * 
	 * @param distance the new maximum distance for entity navigation (in blocks). The default value is 200 blocks.
	 */
	public void setMaximumWalkingDistance(float distance);
	/**
	 * Gets the maximum distance for entity navigation.
	 * @return the current maximum distance for entity navigation (in blocks). The default value is 200 blocks.
	 */
	public float getMaximumWalkingDistance();
	
	/*
	/**
	 * 
	 * @param distance
	 * @throws IllegalArgumentException
	 
	public void setTeleportTriggerDistance(double distance) throws IllegalArgumentException;
	public double getTeleportTriggerDistance();
	
	public void setTeleportTargetDistance(double distance) throws IllegalArgumentException;
	public double getTeleportTargetDistance();*/
}
