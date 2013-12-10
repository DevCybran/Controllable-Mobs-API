package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import java.util.HashSet;
import java.util.List;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIState;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.AIController;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.CraftAIPart;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalAIMonitor extends PathfinderGoalWrapper {
	private final AIController<?> controller;
	private HashSet<PathfinderGoal> lastActive;
	
	public PathfinderGoalAIMonitor(AIController<?> controller, HashSet<PathfinderGoal> defaultActive) {
		this.controller = controller;
		this.lastActive = defaultActive;
	}
	
	public void reset() {
		this.lastActive.clear();
	}

	@Override
	protected boolean canStart() {
		HashSet<PathfinderGoal> deactivate = new HashSet<PathfinderGoal>(this.lastActive);
		final List<Object> activeItems = NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_ACTIVEGOALITEMS.get(this.controller.selector);
		PathfinderGoal goal;
		for(Object item: activeItems) {
			goal = NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_GOAL.get(item);
			if(goal instanceof PathfinderGoalActionBase) continue;
			if(!deactivate.remove(goal)) {
				this.controller.goalPartMap.get(goal).setState(AIState.ACTIVE);
				this.lastActive.add(goal);
			}
		}
		CraftAIPart<?,?> part;
		for(PathfinderGoal disGoal: deactivate) {
			part = this.controller.goalPartMap.get(disGoal);
			if(part!=null) part.setState(AIState.INACTIVE);
			this.lastActive.remove(disGoal);
		}
		return false;
	}

	@Override
	protected void onStart() {}

	@Override
	protected boolean canContinue() {
		return false;
	}

	@Override
	protected void onTick() {}

	@Override
	protected void onEnd() {}

}
