package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import net.minecraft.server.v1_6_R3.PathEntity;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionFollow;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalActionFollow extends PathfinderGoalActionDelayed<ControllableMobActionFollow> {
	private PathEntity path;

	public PathfinderGoalActionFollow(CraftControllableMob<?> mob) {
		super(mob, ActionType.FOLLOW);
		this.setMutexBits(3);
	}
	
	@Override
	protected boolean isActionRequired() {
		return NativeInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.mob.notchEntity, action.target) > action.maximumDistanceSquared;
	}
	
	@Override
	protected boolean isActionBlocked() {
		return mob.notchEntity.world!=action.target.world;
	}
	
	@Override
	protected boolean canStartAction() {
		this.mob.adjustMaximumNavigationDistance((float) Math.sqrt(NativeInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.mob.notchEntity, action.target)));
		final PathEntity path = NativeInterfaces.NAVIGATION.METHOD_CREATEPATHTOENTITY.invoke(this.mob.notchEntity.getNavigation(), this.action.target);
		if(path==null) return false;
		this.path = path;
		return true;
	}

	@Override
	protected void onStartAction() {
		NativeInterfaces.NAVIGATION.METHOD_MOVEALONGPATH.invoke(this.mob.notchEntity.getNavigation(), this.path, 1.0);
	}

	@Override
	protected boolean canContinueAction() {
		return !NativeInterfaces.NAVIGATION.METHOD_ISNOTMOVING.invoke(this.mob.notchEntity.getNavigation()) && (NativeInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.mob.notchEntity, this.action.target)>this.action.minimumDistanceSquared);
	}

	@Override
	protected void onEndAction() {
		NativeInterfaces.NAVIGATION.METHOD_STOP.invoke(this.mob.notchEntity.getNavigation());
	}

	@Override
	protected int getDelayTicks() {
		return 1;
	}

}
