package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import org.bukkit.Location;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;

public class ControllableMobActionAttackMove extends ControllableMobActionAbstractMove {
	public final double maxDistraction;
	public double lastDistanceSquared = Double.MAX_VALUE;
	
	public ControllableMobActionAttackMove(ControllableMobActionManager manager, Location to, double movementSpeedMultiplicator, double maxDistraction) {
		super(manager, ActionType.ATTACKMOVE, to, movementSpeedMultiplicator);
		if(this.isValid()) {
			this.maxDistraction = 0;
		} else {
			this.maxDistraction = Math.abs(maxDistraction);
		}
	}

}
