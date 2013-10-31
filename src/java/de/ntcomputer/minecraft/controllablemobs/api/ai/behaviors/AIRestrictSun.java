package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import org.bukkit.entity.Creature;

import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.PathfinderGoal;
import net.minecraft.server.v1_6_R3.PathfinderGoalRestrictSun;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * An AIBehavior that will force the entity to stay out of the sun.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AIRestrictSun extends AIBehavior<Creature> {
	
	/**
	 * Create with an automatically given priority.
	 */
	public AIRestrictSun() {
		this(0);
	}

	/**
	 * Create with a custom priority.
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AIRestrictSun(final int priority) {
		super(priority);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(final CraftControllableMob<? extends Creature> mob) {
		return new PathfinderGoalRestrictSun((EntityCreature) mob.notchEntity);
	}

	@Override
	public AIType getType() {
		return AIType.MOVE_RESTRICTSUN;
	}

}
