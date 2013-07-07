package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_5_R3.EntityCreature;
import net.minecraft.server.v1_5_R3.PathfinderGoal;
import net.minecraft.server.v1_5_R3.PathfinderGoalFleeSun;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * An AIBehavior that will force the entity to move out of the sun.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AIFleeSun extends AIBehavior {
	
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
		super(priority);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(final CraftControllableMob<?> mob) {
		return new PathfinderGoalFleeSun((EntityCreature) mob.notchEntity, mob.getProperties().getMovementSpeed());
	}

	@Override
	public AIType getType() {
		return AIType.MOVE_FLEESUN;
	}

}
