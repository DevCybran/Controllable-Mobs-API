package de.ntcomputer.minecraft.controllablemobs.implementation;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobAI;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIPart;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.AIDispatcher;

public class CraftControllableMobAI<E extends LivingEntity> implements ControllableMobAI<E> {
	private AIDispatcher<E> dispatcher;
	
	CraftControllableMobAI(final CraftControllableMob<E> mob) {
		this.dispatcher = new AIDispatcher<E>(mob);
	}
	
	void dispose() {
		this.dispatcher.dispose();
		this.dispatcher = null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Deprecated
	@Override
	public void addAIBehavior(AIBehavior behavior) throws IllegalArgumentException {
		this.addBehavior(behavior);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Deprecated
	@Override
	public void addAIBehaviors(AIBehavior[] behaviors) throws IllegalArgumentException {
		this.addBehaviors(behaviors);
	}

	@SuppressWarnings("rawtypes")
	@Deprecated
	@Override
	public void removeAIBehavior(AIBehavior behavior) throws IllegalArgumentException {
		if(behavior==null) throw new IllegalArgumentException("the AIBehavior must not be null");
		this.dispatcher.remove(behavior);
	}

	@Deprecated
	@Override
	public void clearAIBehaviors() {
		this.clear();
	}

	@Deprecated
	@Override
	public void restoreAIBehaviors() {
		this.reset();
	}

	@SuppressWarnings({ "rawtypes" })
	@Deprecated
	@Override
	public AIBehavior[] getAIBehaviors() {
		ArrayList<AIBehavior> behaviors = new ArrayList<AIBehavior>();
		this.dispatcher.getBehaviors(behaviors);
		return behaviors.toArray(new AIBehavior[0]);
	}

	@Override
	public <B extends AIBehavior<? super E>> AIPart<E,B> addBehavior(B behavior) throws IllegalArgumentException {
		if(behavior==null) throw new IllegalArgumentException("the AIBehavior must not be null");
		return this.dispatcher.add(behavior);
	}

	@Override
	public AIPart<E,?>[] addBehaviors(AIBehavior<? super E>... behaviors) throws IllegalArgumentException {
		if(behaviors==null) throw new IllegalArgumentException("the AIBehavior-Array must not be null");
		for(int i=0; i<behaviors.length; i++) {
			if(behaviors[i]==null) throw new IllegalArgumentException("the AIBehavior["+i+"] must not be null");
		}
		@SuppressWarnings("unchecked")
		AIPart<E,?>[] parts = new AIPart[behaviors.length];
		for(int i=0; i<behaviors.length; i++) {
			parts[i] = this.dispatcher.add(behaviors[i]);
		}
		return parts;
	}

	@Override
	public void remove(AIType... typesToRemove) {
		this.dispatcher.remove(typesToRemove, true);
	}

	@Override
	public void removeExcept(AIType... typesToKeep) {
		this.dispatcher.remove(typesToKeep, false);
	}

	@Override
	public void clear() {
		this.dispatcher.clear();
	}

	@Override
	public void reset() {
		this.dispatcher.reset();
	}

	@SuppressWarnings("unchecked")
	@Override
	public AIPart<E,?>[] getParts() {
		ArrayList<AIPart<E,?>> parts = new ArrayList<AIPart<E,?>>();
		this.dispatcher.get(parts,null);
		return parts.toArray(new AIPart[0]);
	}

	@Override
	public boolean hasBehavior(AIType type) {
		return this.dispatcher.contains(type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AIPart<E, ?>[] getPartsOf(AIType... types) {
		ArrayList<AIPart<E,?>> parts = new ArrayList<AIPart<E,?>>();
		this.dispatcher.get(parts,types);
		return parts.toArray(new AIPart[0]);
	}

}
