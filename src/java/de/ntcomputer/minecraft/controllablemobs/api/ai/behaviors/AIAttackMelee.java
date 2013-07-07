package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_5_R3.PathfinderGoal;
import net.minecraft.server.v1_5_R3.PathfinderGoalMeleeAttack;
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
public class AIAttackMelee extends AIBehavior {
	
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
		super(priority);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(final CraftControllableMob<?> mob) {
		return new PathfinderGoalMeleeAttack(mob.notchEntity, mob.getProperties().getMovementSpeed(), false);
	}

	@Override
	public AIType getType() {
		return AIType.ATTACK_MELEE;
	}

}
