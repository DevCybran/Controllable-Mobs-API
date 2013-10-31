package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import net.minecraft.server.v1_6_R3.PathfinderGoal;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMob;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIPart;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIState;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior;

public class CraftAIPart<E extends LivingEntity, B extends AIBehavior<? super E>> implements AIPart<E,B> {
	private final B behavior;
	final int priority;
	private final AIType type;
	private final AIController<E> controller;
	final PathfinderGoal goal;
	private AIState state = AIState.INACTIVE;
	
	CraftAIPart(AIController<E> controller, int priority, PathfinderGoal pfg) {
		this.controller = controller;
		this.behavior = null;
		this.priority = priority;
		controller.lastBehaviorPriority = Math.max(controller.lastBehaviorPriority, priority);
		this.goal = pfg;
		this.type = AIType.getTypeByInstance(pfg);
	}
	
	CraftAIPart(AIController<E> controller, B behavior) {
		this.controller = controller;
		this.behavior = behavior;
		this.priority = behavior.getPriority(controller.lastBehaviorPriority);
		controller.lastBehaviorPriority = Math.max(controller.lastBehaviorPriority, this.priority);
		this.goal = behavior.createPathfinderGoal(controller.mob);
		this.type = behavior.getType();
	}
	
	
	// INTERNAL
	
	public void setState(AIState state) {
		this.state = state;
	}
	
	
	// PUBLIC

	@Override
	public boolean isCustomized() {
		return this.behavior!=null;
	}

	@Override
	public B getBehavior() {
		return this.behavior;
	}

	@Override
	public AIState getState() {
		return this.state;
	}

	@Override
	public AIType getType() {
		return this.type;
	}

	@Override
	public void unattach() {
		if(this.state!=AIState.UNATTACHED) this.controller.unattach(this);
	}

	@Override
	public void reattach() throws IllegalStateException {
		if(this.state==AIState.UNATTACHED) this.controller.attachAndSort(this);
	}

	@Override
	public ControllableMob<E> getControllableMob() {
		return this.controller.mob;
	}

}
