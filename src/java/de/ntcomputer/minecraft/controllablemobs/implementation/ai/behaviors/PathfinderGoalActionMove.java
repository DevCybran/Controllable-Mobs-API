package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import net.minecraft.server.v1_6_R2.PathEntity;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionMove;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NmsInterfaces;

public class PathfinderGoalActionMove extends PathfinderGoalActionDelayed<ControllableMobActionMove> {
	private PathEntity path;

	public PathfinderGoalActionMove(CraftControllableMob<?> mob) {
		super(mob, ActionType.MOVE);
		this.setMutexBits(3);
	}
	
	@Override
	protected boolean canStartAction() {
		if(action.world!=this.mob.notchEntity.world) return false;
		this.mob.adjustMaximumNavigationDistance((float) NmsInterfaces.ENTITY.METHOD_GETDISTANCETOLOCATION.invoke(this.mob.notchEntity, action.x, action.y, action.z));
		final PathEntity path = NmsInterfaces.NAVIGATION.METHOD_CREATEPATHTOLOCATION.invoke(this.mob.notchEntity.getNavigation(), action.x, action.y, action.z);
		//final PathEntity path = NativeInterfaces.WORLD.METHOD_GENERATEPATHTOCOORDS.invoke(this.mob.notchEntity.world, this.mob.notchEntity, action.x, action.y, action.z, 100F, true, false, false, true);
		if(path==null) return false;
		this.path = path;
		return true;
	}

	@Override
	protected void onStartAction() {
		NmsInterfaces.NAVIGATION.METHOD_MOVEALONGPATH.invoke(this.mob.notchEntity.getNavigation(), this.path, this.mob.getProperties().getMovementSpeed());
	}

	@Override
	protected boolean canContinueAction() {
		return !NmsInterfaces.NAVIGATION.METHOD_ISNOTMOVING.invoke(this.mob.notchEntity.getNavigation());
	}

	@Override
	protected void onEndAction() {
		NmsInterfaces.NAVIGATION.METHOD_STOP.invoke(this.mob.notchEntity.getNavigation());
	}

	@Override
	protected int getDelayTicks() {
		return 1;
	}

}
