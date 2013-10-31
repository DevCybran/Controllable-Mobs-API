package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalFloat;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * Very important AIBehavior that lets the entity swim.
 * Must be added if the entity should not drown when it gets into the water.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AIFloat extends AIBehavior<LivingEntity> {
	
	/**
	 * Create with an automatically given priority.
	 */
	public AIFloat() {
		this(0);
	}

	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AIFloat(final int priority) {
		super(priority);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<?> mob) {
		return new PathfinderGoalFloat(mob.notchEntity);
	}

	@Override
	public AIType getType() {
		return AIType.MOVE_FLOAT;
	}

}
