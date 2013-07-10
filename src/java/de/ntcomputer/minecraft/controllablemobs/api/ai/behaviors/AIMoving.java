package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import org.bukkit.entity.LivingEntity;

public abstract class AIMoving<E extends LivingEntity> extends AIBehavior<E> {
	protected final double movementSpeedMultiplicator;

	public AIMoving(int priority, double movementSpeedMultiplicator) {
		super(priority);
		this.movementSpeedMultiplicator = movementSpeedMultiplicator;
	}

}
