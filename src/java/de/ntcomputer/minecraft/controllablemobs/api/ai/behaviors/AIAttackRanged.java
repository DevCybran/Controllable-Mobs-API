package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.IRangedEntity;
import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalArrowAttack;

import org.bukkit.entity.Creature;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * Attack behavior: An AI behavior that lets an entity attack its target with a ranged attack, based on the entity class.
 * You must not add this behavior to an entity which is not a Skeleton, Snowman, Witch or Wither. 
 * The entity will also move toward its target until it is in range for an attack.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AIAttackRanged extends AIMoving<Creature> {
	private final int attackTicks;
	private final float maximumRange;
	
	/**
	 * Create with an automatically given priority.
	 * 
	 * @see AIAttackRanged#AIAttackRanged(int, double, float, int)
	 */
	public AIAttackRanged() {
		this(0);
	}
	
	/**
	 * Create with a custom priority.
	 * 
	 * @see AIAttackRanged#AIAttackRanged(int, double, float, int)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AIAttackRanged(int priority) {
		this(priority, 1.0);
	}
	
	/**
	 * Create with a custom priority and a custom movement speed multiplicator
	 * 
	 * @see AIAttackRanged#AIAttackRanged(int, double, float, int)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param movementSpeedMultiplicator the entity's movement speed is multiplied with this multiplicator when moving to the target
	 */
	public AIAttackRanged(int priority, double movementSpeedMultiplicator) {
		this(priority, movementSpeedMultiplicator, 16.0f);
	}
	
	/**
	 * Create with a custom priority, a custom movement speed multiplicator and a custom maximum range for the attacks.
	 * 
	 * @see AIAttackRanged#AIAttackRanged(int, double, float, int)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param movementSpeedMultiplicator the entity's movement speed is multiplied with this multiplicator when moving to the target
	 * @param maximumRange the maximum range a projectile can be shot at, in blocks. The default range is 16.0 blocks
	 */
	public AIAttackRanged(int priority, double movementSpeedMultiplicator, float maximumRange) {
		this(priority, movementSpeedMultiplicator, maximumRange, 60);
	}

	/**
	 * Create with a custom priority, a custom movement speed multiplicator, a custom attack speed and a custom maximum range for the attacks.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param movementSpeedMultiplicator the entity's movement speed is multiplied with this multiplicator when moving to the target
	 * @param maximumRange the maximum range a projectile can be shot at, in blocks. The default range is 16.0 blocks
	 * @param attackTicks the amount of server ticks that are passed between two attacks. the default value is 60 = one attack every 3 seconds
	 */
	public AIAttackRanged(int priority, double movementSpeedMultiplicator, float maximumRange, int attackTicks) {
		super(priority,movementSpeedMultiplicator);
		this.attackTicks = attackTicks;
		this.maximumRange = maximumRange;
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends Creature> mob) {
		return new PathfinderGoalArrowAttack((IRangedEntity) mob.notchEntity, this.movementSpeedMultiplicator, this.attackTicks, this.maximumRange);
	}

	@Override
	public AIType getType() {
		return AIType.ATTACK_RANGED;
	}

}
