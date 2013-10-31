package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import org.bukkit.entity.Creature;

import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalFleeSun;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * An AIBehavior that will force the entity to move out of the sun.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AIFleeSun extends AIMoving<Creature> {
	
	/**
	 * Create with an automatically given priority.
	 */
	public AIFleeSun() {
		this(0);
	}

	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AIFleeSun(final int priority) {
		this(priority,1.0);
	}
	
	/**
	 * Create with a custom priority and a custom movement speed multiplicator 
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param movementSpeedMultiplicator the entity's movement speed is multiplied with this multiplicator when moving to the target
	 */
	public AIFleeSun(final int priority, double movementSpeedMultiplicator) {
		super(priority,movementSpeedMultiplicator);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends Creature> mob) {
		return new PathfinderGoalFleeSun((EntityCreature) mob.notchEntity, this.movementSpeedMultiplicator);
	}

	@Override
	public AIType getType() {
		return AIType.MOVE_FLEESUN;
	}

}
