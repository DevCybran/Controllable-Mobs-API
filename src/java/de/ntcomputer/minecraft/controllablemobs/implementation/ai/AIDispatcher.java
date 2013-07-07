package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIPart;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AITargetBehavior;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

public class AIDispatcher {
	private AIController goalController;
	private AIController targetController;
	
	public AIDispatcher(CraftControllableMob<?> mob) {
		this.goalController = new AIGoalController(mob);
		this.targetController = new AITargetController(mob);
	}
	
	public AIPart add(AIBehavior behavior) throws IllegalArgumentException {
		if(isTarget(behavior)) return this.targetController.add(behavior);
		else return this.goalController.add(behavior);
	}
	
	@Deprecated
	public void remove(AIBehavior behavior) throws IllegalArgumentException {
		if(isTarget(behavior)) this.targetController.remove(behavior);
		else this.goalController.remove(behavior);
	}
	
	public void remove(AIType[] types, boolean remove) {
		Set<AIType> typeSet = new HashSet<AIType>();
		Collections.addAll(typeSet, types);
		this.goalController.remove(typeSet, remove);
		this.targetController.remove(typeSet, remove);
	}
	
	@Deprecated
	public void getBehaviors(List<AIBehavior> list) {
		this.goalController.getBehaviors(list);
		this.targetController.getBehaviors(list);
	}
	
	public void get(List<AIPart> list) {
		this.goalController.get(list);
		this.goalController.get(list);
	}
	
	public void clear() {
		this.goalController.clear();
		this.targetController.clear();
	}
	
	public void reset() {
		this.goalController.reset();
		this.targetController.reset();
	}
	
	private boolean isTarget(AIBehavior behavior) {
		return behavior instanceof AITargetBehavior;
	}
	
	public void dispose() {
		this.targetController.dispose();
		this.goalController.dispose();
		this.targetController = null;
		this.goalController = null;
	}

}
