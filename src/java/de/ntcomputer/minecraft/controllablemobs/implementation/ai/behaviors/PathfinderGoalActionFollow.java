package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import net.minecraft.server.v1_6_R2.PathEntity;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionFollow;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NmsInterfaces;

public class PathfinderGoalActionFollow extends PathfinderGoalActionDelayed<ControllableMobActionFollow> {
	private PathEntity path;

	public PathfinderGoalActionFollow(CraftControllableMob<?> mob) {
		super(mob, ActionType.FOLLOW);
		this.setMutexBits(3);
	}
	
	@Override
	protected boolean isActionRequired() {
		return NmsInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.mob.notchEntity, action.target) > action.maximumDistanceSquared;
	}
	
	@Override
	protected boolean isActionBlocked() {
		return mob.notchEntity.world!=action.target.world;
	}
	
	@Override
	protected boolean canStartAction() {
		this.mob.adjustMaximumNavigationDistance((float) Math.sqrt(NmsInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.mob.notchEntity, action.target)));
		final PathEntity path = NmsInterfaces.NAVIGATION.METHOD_CREATEPATHTOENTITY.invoke(this.mob.notchEntity.getNavigation(), this.action.target);
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
		return !NmsInterfaces.NAVIGATION.METHOD_ISNOTMOVING.invoke(this.mob.notchEntity.getNavigation()) && (NmsInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.mob.notchEntity, this.action.target)>this.action.minimumDistanceSquared);
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
