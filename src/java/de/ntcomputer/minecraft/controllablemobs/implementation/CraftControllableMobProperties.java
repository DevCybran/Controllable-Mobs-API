package de.ntcomputer.minecraft.controllablemobs.implementation;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobAttributes;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class CraftControllableMobProperties implements ControllableMobAttributes {
	private CraftControllableMob<?> mob;
	private final float defaultMovementSpeed;
	private float movementSpeed;
	private final float defaultMaximumNavigationDistance;
	private float maximumNavigationDistance = 200;
	/*private float teleportTriggerDistance = 120;
	private float teleportTargetDistance = 100;*/
	
	CraftControllableMobProperties(final CraftControllableMob<?> mob) {
		this.mob = mob;
		this.defaultMovementSpeed = this.movementSpeed = NativeInterfaces.ENTITYLIVING.FIELD_MOVEMENTSPEED.get(mob.notchEntity);
		this.defaultMaximumNavigationDistance = NativeInterfaces.NAVIGATION.FIELD_MAXPATHLENGTH.get(mob.notchEntity.getNavigation());
	}
	
	public void adjustMaximumNavigationDistance(final float forDistance) {
		if(this.maximumNavigationDistance==0 || this.maximumNavigationDistance>=forDistance) {
			NativeInterfaces.NAVIGATION.FIELD_MAXPATHLENGTH.set(this.mob.notchEntity.getNavigation(), Math.max(forDistance, 16));
		}
	}
	

	@Override
	public void setMovementSpeed(float movementSpeed) {
		if(movementSpeed<0.01 || movementSpeed>2) return;
		this.movementSpeed = movementSpeed;
		NativeInterfaces.ENTITYLIVING.FIELD_MOVEMENTSPEED.set(this.mob.notchEntity, movementSpeed);
	}

	@Override
	public float getMovementSpeed() {
		return this.movementSpeed;
	}
	
	void dispose() {
		this.setMovementSpeed(this.defaultMovementSpeed);
		NativeInterfaces.NAVIGATION.FIELD_MAXPATHLENGTH.set(this.mob.notchEntity.getNavigation(), this.defaultMaximumNavigationDistance);
		this.mob = null;
	}

	@Override
	public void setMaximumWalkingDistance(float distance) throws IllegalArgumentException {
		if(distance<0) throw new IllegalArgumentException("distance must not be negative");
		if(distance>0 && distance<16) throw new IllegalArgumentException("distance has to be greater than or equal 16 blocks");
		this.maximumNavigationDistance = distance;
		if(distance!=0 && NativeInterfaces.NAVIGATION.FIELD_MAXPATHLENGTH.get(this.mob.notchEntity.getNavigation()) > distance) {
			NativeInterfaces.NAVIGATION.FIELD_MAXPATHLENGTH.set(this.mob.notchEntity.getNavigation(), distance);
		}
	}

	@Override
	public float getMaximumWalkingDistance() {
		return this.maximumNavigationDistance;
	}

	/*@Override
	public void setTeleportTriggerDistance(double distance) throws IllegalArgumentException {
		if(distance<0) throw new IllegalArgumentException("distance must not be negative");
		if(distance>0 && distance<=16) throw new IllegalArgumentException("distance has to be greater than 16.0 blocks");
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTeleportTriggerDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTeleportTargetDistance(double distance) throws IllegalArgumentException {
		if(distance<0) throw new IllegalArgumentException("distance must not be negative");
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTeleportTargetDistance() {
		// TODO Auto-generated method stub
		return 0;
	}*/

}
