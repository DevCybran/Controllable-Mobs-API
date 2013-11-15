package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import org.bukkit.Location;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;

public class ControllableMobActionMove extends ControllableMobActionAbstractMove {
	
	public ControllableMobActionMove(ControllableMobActionManager manager, Location to, double movementSpeedMultiplicator) {
		super(manager, ActionType.MOVE, to, movementSpeedMultiplicator);
	}

}
