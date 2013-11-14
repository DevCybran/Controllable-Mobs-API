package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalBreakDoor;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * If added, this behavior lets entities break wooden doors when needed.
 * 
 * @author DevCybran
 * @version v6
 *
 */
public final class AIDoorBreak extends AIBehavior<LivingEntity> {
	
	/**
	 * Create with an automatically given priority.
	 */
	public AIDoorBreak() {
		this(0);
	}

	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AIDoorBreak(int priority) {
		super(priority);
	}

	@Override
	public AIType getType() {
		return AIType.ACTION_DOORBREAK;
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends LivingEntity> mob) {
		return new PathfinderGoalBreakDoor(mob.nmsEntity);
	}

}
