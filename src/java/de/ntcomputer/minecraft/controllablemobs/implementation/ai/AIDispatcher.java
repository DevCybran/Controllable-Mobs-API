package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIPart;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AITargetBehavior;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

public class AIDispatcher<E extends LivingEntity> {
	private AIController<E> goalController;
	private AIController<E> targetController;
	
	public AIDispatcher(CraftControllableMob<E> mob) {
		this.goalController = new AIGoalController<E>(mob);
		this.targetController = new AITargetController<E>(mob);
	}
	
	public <B extends AIBehavior<? super E>> AIPart<E,B> add(B behavior) throws IllegalArgumentException {
		if(isTarget(behavior)) return this.targetController.add(behavior);
		else return this.goalController.add(behavior);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	
	@SuppressWarnings("rawtypes")
	@Deprecated
	public void getBehaviors(List<AIBehavior> list) {
		this.goalController.getBehaviors(list);
		this.targetController.getBehaviors(list);
	}
	
	public void get(List<AIPart<E,?>> list) {
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
	
	private boolean isTarget(AIBehavior<? super E> behavior) {
		return behavior instanceof AITargetBehavior;
	}
	
	public void dispose() {
		this.targetController.dispose();
		this.goalController.dispose();
		this.targetController = null;
		this.goalController = null;
	}

}
