package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R2.PathfinderGoal;

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
 */
public abstract class AIBehavior<E extends LivingEntity> {
	private final int priority;
	
	protected AIBehavior(int priority) {
		this.priority = priority;
	}
	
	public final int getPriority(int lastBehaviorPriority) {
		return this.priority<=0 ? lastBehaviorPriority+1 : this.priority;
	}
	
	public abstract AIType getType();

	public abstract PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends E> mob);
	
}
