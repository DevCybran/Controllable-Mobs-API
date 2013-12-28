package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.server.v1_7_R1.EntityInsentient;
import net.minecraft.server.v1_7_R1.PathfinderGoal;
import net.minecraft.server.v1_7_R1.PathfinderGoalSelector;

import org.bukkit.craftbukkit.v1_7_R1.util.UnsafeList;
import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIState;
import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIBehavior;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalAIMonitor;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalWrapper;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;

public abstract class AIController<E extends LivingEntity> implements Comparator<Object> {
	private List<PathfinderGoalWrapper> actionGoals;
	private List<CraftAIPart<E,?>> attachedParts;
	private List<CraftAIPart<E,?>> defaultParts;
	private PathfinderGoalAIMonitor monitor;
	CraftControllableMob<E> mob;
	int lastBehaviorPriority = 1;
	public PathfinderGoalSelector selector;
	public Map<PathfinderGoal,CraftAIPart<E,?>> goalPartMap;
	
	public AIController(CraftControllableMob<E> mob, NativeFieldObject<EntityInsentient,PathfinderGoalSelector> selectorField) {
		this.mob = mob;
		this.selector = selectorField.get(mob.nmsEntity);
		
		// create custom parts list
		this.attachedParts = new ArrayList<CraftAIPart<E,?>>();
		this.goalPartMap = new HashMap<PathfinderGoal,CraftAIPart<E,?>>();
		
		// copy default items
		this.defaultParts = new ArrayList<CraftAIPart<E,?>>();
		HashSet<PathfinderGoal> activeDefaultGoals = new HashSet<PathfinderGoal>();
		List<Object> defaultItems = NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_GOALITEMS.get(this.selector);
		CraftAIPart<E,?> part;
		for(Object item: defaultItems) {
			part = new CraftAIPart<E,AIBehavior<? super E>>(this, NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_PRIORITY.get(item), NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_GOAL.get(item));
			if(NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_ACTIVEGOALITEMS.get(selector).contains(item)) {
				part.setState(AIState.ACTIVE);
				activeDefaultGoals.add(part.goal);
			}
			this.defaultParts.add(part);
			this.attachedParts.add(part);
			this.goalPartMap.put(part.goal, part);
		}
		
		// create action goals and monitor and sort with default goals
		this.actionGoals = new ArrayList<PathfinderGoalWrapper>();
		this.createActionGoals();
		this.monitor = new PathfinderGoalAIMonitor(this,activeDefaultGoals);
		this.addActionGoal(0, monitor);
		this.sortGoals();
	}
	
	
	// action goal methods
	
	protected abstract void createActionGoals();
	
	protected void addActionGoal(final int priority, final PathfinderGoalWrapper actionGoal) {
		this.actionGoals.add(actionGoal);
		this.addGoal(priority, actionGoal);
	}
	
	
	// internal goal modifications
	
	private void addGoal(int priority, PathfinderGoal goal) {
		NativeInterfaces.PATHFINDERGOALSELECTOR.METHOD_ADDPATHFINDERGOAL.invoke(this.selector, priority, goal);
	}
	
	private void removeGoal(PathfinderGoal goal) {
		PathfinderGoal searchGoal;
		
		// shutdown and remove active item
		Iterator<Object> iterator = NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_ACTIVEGOALITEMS.get(this.selector).iterator();
		while(iterator.hasNext()) {
			searchGoal = NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_GOAL.get(iterator.next());
			if(searchGoal==goal) {
				NativeInterfaces.PATHFINDERGOAL.METHOD_ONEND.invoke(searchGoal);
				iterator.remove();
				break;
			}
		}
		((UnsafeList<Object>.Itr) iterator).valid = false;
		
		// remove item
		iterator = NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_GOALITEMS.get(this.selector).iterator();
		while(iterator.hasNext()) {
			searchGoal = NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_GOAL.get(iterator.next());
			if(searchGoal==goal) {
				iterator.remove();
				break;
			}
		}
		((UnsafeList<Object>.Itr) iterator).valid = false;
	}
	
	private void clearGoals() {
		PathfinderGoal searchGoal;
		
		// shutdown and remove active non-action items
		Iterator<Object> iterator = NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_ACTIVEGOALITEMS.get(this.selector).iterator();
		while(iterator.hasNext()) {
			searchGoal = NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_GOAL.get(iterator.next());
			if(!this.actionGoals.contains(searchGoal)) {
				NativeInterfaces.PATHFINDERGOAL.METHOD_ONEND.invoke(searchGoal);
				iterator.remove();
			}
		}
		((UnsafeList<Object>.Itr) iterator).valid = false;
		
		// remove all non-action items
		iterator = NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_GOALITEMS.get(this.selector).iterator();
		while(iterator.hasNext()) {
			searchGoal = NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_GOAL.get(iterator.next());
			if(!this.actionGoals.contains(searchGoal) ) iterator.remove();
		}
		((UnsafeList<Object>.Itr) iterator).valid = false;
	}
	
	private void sortGoals() {
		Collections.sort(NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_GOALITEMS.get(this.selector), this);
	}
	
	@Override
	public int compare(Object goalitem1, Object goalitem2) {
		return NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_PRIORITY.get(goalitem1) <= NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_PRIORITY.get(goalitem2) ? -1 : 1;
	}
	
	
	// external AI access
	
	<B extends AIBehavior<? super E>> CraftAIPart<E,B> add(B behavior) {
		CraftAIPart<E,B> part = new CraftAIPart<E,B>(this, behavior);
		this.attachAndSort(part);
		return part;
	}
	
	private void attach(CraftAIPart<E,?> part) {
		AIComponentHandlers.handleAdd(mob, part.goal);
		this.addGoal(part.priority, part.goal);
		this.attachedParts.add(part);
		this.goalPartMap.put(part.goal, part);
		part.setState(AIState.INACTIVE);
	}
	
	void attachAndSort(CraftAIPart<E,?> part) throws IllegalStateException {
		this.disposedCall();
		this.attach(part);
		this.sortGoals();
	}
	
	void unattach(CraftAIPart<E,?> part) {
		this.removeGoal(part.goal);
		this.attachedParts.remove(part);
		this.goalPartMap.remove(part.goal);
		part.setState(AIState.UNATTACHED);
		AIComponentHandlers.handleRemoved(mob, part.goal);
	}
	
	void remove(Set<AIType> typeSet, boolean remove) {
		List<CraftAIPart<E,?>> toRemove = new LinkedList<CraftAIPart<E,?>>();
		for(CraftAIPart<E,?> searchPart: this.attachedParts) {
			if(! (typeSet.contains(searchPart.getType()) ^ remove)) toRemove.add(searchPart);
		}
		
		for(CraftAIPart<E,?> removePart: toRemove) {
			this.unattach(removePart);
		}
	}
	
	boolean contains(AIType type) {
		for(CraftAIPart<E,?> searchPart: this.attachedParts) {
			if(searchPart.getType()==type) return true;
		}
		return false;
	}
	
	void get(List<? super CraftAIPart<E,?>> list, Set<AIType> types) {
		if(types==null) {
			list.addAll(this.attachedParts);
		} else {
			for(CraftAIPart<E,?> part: this.attachedParts) {
				if(types.contains(part.getType())) list.add(part);
			}
		}
	}
	
	void clear() {
		this.clearGoals();
		PathfinderGoal[] goals = new PathfinderGoal[this.attachedParts.size()];
		int i = 0;
		for(CraftAIPart<E,?> part: this.attachedParts) {
			goals[i++] = part.goal;
			part.setState(AIState.UNATTACHED);
		}
		this.attachedParts.clear();
		this.goalPartMap.clear();
		this.monitor.reset();
		
		for(PathfinderGoal goal: goals) {
			AIComponentHandlers.handleRemoved(mob, goal);
		}
	}
	
	void reset() {
		this.clear();
		for(CraftAIPart<E,?> defaultPart: this.defaultParts) {
			this.attach(defaultPart);
		}
	}
	
	
	// disposing
	
	private void disposedCall() throws IllegalStateException {
		if(this.selector==null) throw new IllegalStateException("[ControllableMobsAPI] you must not modify AI parts after the ControllableMob has been released");
	}
	
	void dispose() {
		// shutdown and remove active items
		final List<Object> activeItems = NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_ACTIVEGOALITEMS.get(this.selector);
		for(Object item: activeItems) {
			NativeInterfaces.PATHFINDERGOAL.METHOD_ONEND.invoke(NativeInterfaces.PATHFINDERGOALSELECTORITEM.FIELD_GOAL.get(item));
		}
		activeItems.clear();
		
		// remove all items
		NativeInterfaces.PATHFINDERGOALSELECTOR.FIELD_GOALITEMS.get(this.selector).clear();
		
		// restore default items
		for(CraftAIPart<E,?> defaultPart: this.defaultParts) {
			this.attach(defaultPart);
		}
		
		// set all AI parts to unattached
		for(CraftAIPart<E,?> part: this.attachedParts) {
			part.setState(AIState.UNATTACHED);
		}
		this.attachedParts.clear();
		this.goalPartMap.clear();
		this.defaultParts.clear();
		
		// nullify
		this.monitor = null;
		this.attachedParts = null;
		this.goalPartMap = null;
		this.defaultParts = null;
		this.actionGoals = null;
		this.selector = null;
		this.mob = null;
	}

}
