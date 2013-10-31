package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalRandomStroll;

import org.bukkit.entity.Creature;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * If this behavior is added, the entity will curiously move around when it is idle.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AIRandomStroll extends AIMoving<Creature> {
	
	/**
	 * Create with an automatically given priority.
	 */
	public AIRandomStroll() {
		this(0);
	}

	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AIRandomStroll(final int priority) {
		this(priority,1.0);
	}
	
	/**
	 * Create with a custom priority and a custom movement speed multiplicator 
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param movementSpeedMultiplicator the entity's movement speed is multiplied with this multiplicator when moving to the target
	 */
	public AIRandomStroll(final int priority, double movementSpeedMultiplicator) {
		super(priority,movementSpeedMultiplicator);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(final CraftControllableMob<? extends Creature> mob) {
		return new PathfinderGoalRandomStroll((EntityCreature) mob.notchEntity, this.movementSpeedMultiplicator);
	}

	@Override
	public AIType getType() {
		return AIType.MOVE_RANDOMSTROLL;
	}

}
