package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.PathfinderGoal;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * The base class for AI behaviors.
 * You should not call any methods of this class!
 * 
 * @author Cybran
 * @version v4
 * 
 * @param <E> a required entity supertype. For example, {@link AIAttackMelee} requires the entity to be a {@link org.bukkit.entity.Creature}.
 *
 */
public abstract class AIBehavior<E extends LivingEntity> {
	private final int priority;
	
	protected AIBehavior(int priority) {
		this.priority = priority;
	}
	
	public final int getPriority(int lastBehaviorPriority) {
		return this.priority<=0 ? lastBehaviorPriority+1 : this.priority;
	}
	
	/**
	 * @return the type of this behavior
	 */
	public abstract AIType getType();

	public abstract PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends E> mob);
	
}
