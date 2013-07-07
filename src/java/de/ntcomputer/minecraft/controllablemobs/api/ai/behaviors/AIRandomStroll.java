package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_5_R3.EntityCreature;
import net.minecraft.server.v1_5_R3.PathfinderGoal;
import net.minecraft.server.v1_5_R3.PathfinderGoalRandomStroll;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * If this behavior is added, the entity will curiously move around when it is idle.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AIRandomStroll extends AIBehavior {
	
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
		super(priority);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(final CraftControllableMob<?> mob) {
		return new PathfinderGoalRandomStroll((EntityCreature) mob.notchEntity, mob.getProperties().getMovementSpeed());
	}

	@Override
	public AIType getType() {
		return AIType.MOVE_RANDOMSTROLL;
	}

}
