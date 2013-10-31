package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalMeleeAttack;

import org.bukkit.entity.Creature;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * Attack behavior: An AI behavior that lets an entity attack its target with a melee attack.
 * This will also make the entity constantly run to its target.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AIAttackMelee extends AIMoving<Creature> {

	/**
	 * Create with an automatically given priority.
	 */
	public AIAttackMelee() {
		this(0);
	}

	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AIAttackMelee(final int priority) {
		this(priority,1.0);
	}
	
	/**
	 * Create with a custom priority and a custom speed modifier.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param movementSpeedMultiplicator the entity's movement speed is multiplied with this multiplicator when moving to the target
	 */
	public AIAttackMelee(int priority, double movementSpeedMultiplicator) {
		super(priority,movementSpeedMultiplicator);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends Creature> mob) {
		return new PathfinderGoalMeleeAttack((EntityCreature) mob.notchEntity, this.movementSpeedMultiplicator, false);
	}

	@Override
	public AIType getType() {
		return AIType.ATTACK_MELEE;
	}

}
