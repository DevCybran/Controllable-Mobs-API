package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalOpenDoor;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

public final class AIDoorOpen extends AIBehavior<LivingEntity> {
	private final boolean closeDoorAfterwards;
	
	/**
	 * Create with an automatically given priority.
	 */
	public AIDoorOpen() {
		this(0);
	}

	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AIDoorOpen(int priority) {
		this(priority, false);
	}
	
	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param closeDoorAfterwards whether the entity shall close the door after passing through it. default is false
	 */
	public AIDoorOpen(int priority, boolean closeDoorAfterwards) {
		super(priority);
		this.closeDoorAfterwards = closeDoorAfterwards;
	}

	@Override
	public AIType getType() {
		return AIType.ACTION_DOOROPEN;
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends LivingEntity> mob) {
		return new PathfinderGoalOpenDoor(mob.nmsEntity, this.closeDoorAfterwards);
	}

}
