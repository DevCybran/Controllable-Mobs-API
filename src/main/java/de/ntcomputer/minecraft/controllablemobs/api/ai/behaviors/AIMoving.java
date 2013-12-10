package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import org.bukkit.entity.LivingEntity;

/**
 * Abstract class which defines an AI behavior that requires the entity to move.
 * 
 * @author Cybran
 * @version v4
 * 
 */
public abstract class AIMoving<E extends LivingEntity> extends AIBehavior<E> {
	protected final double movementSpeedMultiplicator;

	protected AIMoving(int priority, double movementSpeedMultiplicator) {
		super(priority);
		this.movementSpeedMultiplicator = movementSpeedMultiplicator;
	}

}
