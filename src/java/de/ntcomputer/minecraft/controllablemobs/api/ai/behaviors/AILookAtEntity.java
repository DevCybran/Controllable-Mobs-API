package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalLookAtPlayer;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.ControllableMobHelper;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * The entity will look at another entity when the distance between them is not too long.
 * Usually looks at players, but the corresponding settings can be changed.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AILookAtEntity extends AIBehavior<LivingEntity> {
	private final float maxDistance;
	private final Class<? extends Entity> entityClass;
	
	/**
	 * Create with an automatically given priority.
	 * 
	 * @see AILookAtEntity#AILookAtEntity(int, EntityType, float)
	 */
	public AILookAtEntity() {
		this(0);
	}
	
	/**
	 * Create with a custom priority.
	 * 
	 * @see AILookAtEntity#AILookAtEntity(int, EntityType, float)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AILookAtEntity(final int priority) {
		this(priority, 8.0F);
	}
	
	/**
	 * Create with an automatically given priority but a custom maximum distance.
	 * 
	 * @see AILookAtEntity#AILookAtEntity(int, EntityType, float)
	 * @param maxDistance the maximum distance to look at another entity, in blocks. Default value is 8.0 blocks
	 */
	public AILookAtEntity(final float maxDistance) {
		this(0, maxDistance);
	}
	
	/**
	 * Create with an automatically given priority but a custom target entity type.
	 * 
	 * @see AILookAtEntity#AILookAtEntity(int, EntityType, float)
	 * @param entityType the type of entity, this entity will look at
	 * @throws IllegalArgumentException when the entityType is null or not pointing at a valid entity class
	 */
	public AILookAtEntity(EntityType entityType) throws IllegalArgumentException {
		this(0, entityType);
	}
	
	/**
	 * Create with a custom priority and a custom maximum distance.
	 * 
	 * @see AILookAtEntity#AILookAtEntity(int, EntityType, float)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param maxDistance the maximum distance to look at another entity, in blocks. Default value is 8.0 blocks
	 */
	public AILookAtEntity(final int priority, final float maxDistance) {
		this(priority, EntityHuman.class, maxDistance);
	}
	
	/**
	 * Create with a custom priority and a custom target entity type.
	 * 
	 * @see AILookAtEntity#AILookAtEntity(int, EntityType, float)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param entityType the type of entity, this entity will look at
	 * @throws IllegalArgumentException when the entityType is null or not pointing at a valid entity class
	 */
	public AILookAtEntity(final int priority, final EntityType entityType) throws IllegalArgumentException {
		this(priority, entityType, 8.0F);
	}
	
	/**
	 * Create with an automatically given priority but a custom maximum distance and custom target entity type.
	 * 
	 * @see AILookAtEntity#AILookAtEntity(int, EntityType, float)
	 * @param entityType the type of entity, this entity will look at
	 * @param maxDistance the maximum distance to look at another entity, in blocks. Default value is 8.0 blocks
	 * @throws IllegalArgumentException when the entityType is null or not pointing at a valid entity class
	 */
	public AILookAtEntity(final EntityType entityType, final float maxDistance) throws IllegalArgumentException {
		this(0, entityType, maxDistance);
	}
	
	/**
	 * Create with a custom priority, a custom maximum distance and a custom target entity type.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param entityType the type of entity, this entity will look at
	 * @param maxDistance the maximum distance to look at another entity, in blocks. Default value is 8.0 blocks
	 * @throws IllegalArgumentException when the entityType is null or not pointing at a valid entity class
	 */
	public AILookAtEntity(final int priority, final EntityType entityType, final float maxDistance) throws IllegalArgumentException {
		this(priority, ControllableMobHelper.getNotchEntityClass(entityType), maxDistance);
	}
	
	private AILookAtEntity(final int priority, final Class<? extends Entity> entityClass, final float maxDistance) {
		super(priority);
		this.entityClass = entityClass;
		this.maxDistance = maxDistance;
	}

	@Override
	public PathfinderGoal createPathfinderGoal(final CraftControllableMob<?> mob) {
		return new PathfinderGoalLookAtPlayer(mob.notchEntity, this.entityClass, this.maxDistance);
	}

	@Override
	public AIType getType() {
		return AIType.ACTION_ENTITYLOOK;
	}

}
