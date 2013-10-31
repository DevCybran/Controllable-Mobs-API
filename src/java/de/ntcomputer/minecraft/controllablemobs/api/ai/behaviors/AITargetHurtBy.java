package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalHurtByTarget;

import org.bukkit.entity.Creature;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * Target behavior: When the entity is being attacked, it will attack its attacker back.
 * This new target will override any other target unless you use {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#target(org.bukkit.entity.LivingEntity)}.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AITargetHurtBy extends AITargetBehavior<Creature> {
	private final boolean crowdAttack;
	
	/**
	 * Create with an automatically given priority.
	 */
	public AITargetHurtBy() {
		this(0);
	}
	
	/**
	 * Create with an automatically given priority, optionally causing a crowd attack.
	 * 
	 * @param crowdAttack if set to true, all nearby entities of the same class are working together to kill the attacker (well, theoretical. not tested.)
	 */
	public AITargetHurtBy(final boolean crowdAttack) {
		this(0, crowdAttack);
	}
	
	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AITargetHurtBy(final int priority) {
		this(priority, false);
	}

	/**
	 * Create with a custom priority, optionally causing a crowd attack.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param crowdAttack if set to true, all nearby entities of the same class are working together to kill the attacker (well, theoretical. not tested.)
	 */
	public AITargetHurtBy(final int priority, final boolean crowdAttack) {
		super(priority);
		this.crowdAttack = crowdAttack;
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends Creature> mob) {
		return new PathfinderGoalHurtByTarget((EntityCreature) mob.notchEntity, this.crowdAttack);
	}

	@Override
	public AIType getType() {
		return AIType.TARGET_HURTBY;
	}

}
