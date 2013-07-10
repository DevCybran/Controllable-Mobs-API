package de.ntcomputer.minecraft.controllablemobs.implementation;

import net.minecraft.server.v1_6_R2.EntityInsentient;

import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMob;
import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobAI;
import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions;
import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobProperties;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionManager;

public class CraftControllableMob<E extends LivingEntity> implements ControllableMob<E> {
	private E entity;
	private CraftControllableMobProperties properties;
	private CraftControllableMobAI<E> ai;
	private CraftControllableMobActions actions;
	public EntityInsentient notchEntity;

	public CraftControllableMob(E entity, EntityInsentient notchEntity) {
		this.entity = entity;
		this.notchEntity = notchEntity;
		this.properties = new CraftControllableMobProperties(this);
		this.actions = new CraftControllableMobActions(this);
		this.ai = new CraftControllableMobAI<E>(this);
	}
	
	private void disposedCall() throws IllegalStateException {
		throw new IllegalStateException("[ControllableMobsAPI] the ControllableMob is unassigned");
	}
	
	public void unassign() {
		if(this.entity==null) this.disposedCall();
		
		// component dispose
		this.actions.dispose();
		this.ai.dispose();
		this.properties.dispose();
		
		// component disposal
		this.actions = null;
		this.ai = null;
		this.properties = null;
		
		// entity unassign
		this.notchEntity = null;
		this.entity = null;
	}
	
	public ControllableMobActionManager getActionManager() {
		return this.actions.actionManager;
	}
	
	public void adjustMaximumNavigationDistance(final float forDistance) {
		this.properties.adjustMaximumNavigationDistance(forDistance);
	}
	

	@Override
	public E getEntity() {
		return entity;
	}

	@Override
	public ControllableMobProperties getProperties() throws IllegalStateException {
		if(this.properties==null) this.disposedCall();
		return this.properties;
	}

	@Override
	public ControllableMobAI<E> getAI() {
		if(this.ai==null) this.disposedCall();
		return this.ai;
	}

	@Override
	public ControllableMobActions getActions() {
		if(this.actions==null) this.disposedCall();
		return this.actions;
	}
	
	@Override
    public String toString() {
		if(this.entity==null) return "ControllableMob<[unassigned]>";
		else return "ControllableMob<"+this.entity.toString()+">";
    }

}
