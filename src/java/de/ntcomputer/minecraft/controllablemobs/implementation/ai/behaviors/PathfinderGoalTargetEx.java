package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import net.minecraft.server.v1_6_R3.EntityCreature;
import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.EntityInsentient;
import net.minecraft.server.v1_6_R3.EntityLiving;

import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_6_R3.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;


public abstract class PathfinderGoalTargetEx extends PathfinderGoalWrapper {
	protected final CraftControllableMob<?> mob;
	protected final EntityInsentient entity;
	private final int maximumNoEyeContactTicks;
	protected final boolean ignoreInvulnerability;
	protected final double maximumDistanceSquared;
	protected final Class<? extends EntityLiving>[] targetClasses;
	private int noEyeContactTicks = 0;
	

	public PathfinderGoalTargetEx(CraftControllableMob<?> mob, int maximumNoEyeContactTicks, boolean ignoreInvulnerability, double maximumDistance, Class<? extends EntityLiving>[] targetClasses) {
		this.mob = mob;
		this.entity = mob.notchEntity;
		this.maximumNoEyeContactTicks = maximumNoEyeContactTicks;
		this.ignoreInvulnerability = ignoreInvulnerability;
		this.maximumDistanceSquared = maximumDistance<=0 ? 0 : maximumDistance*maximumDistance;
		this.targetClasses = targetClasses;
		this.setMutexBits(1);
	}
	
	protected boolean target(EntityLiving target, EntityTargetEvent.TargetReason reason) {
		// existence checks
		if(target==null) return false;
		if(target==this.entity) return false;
		if(!target.isAlive()) return false;
		
		// class check
		boolean assignable = false;
		for(Class<? extends EntityLiving> targetClass: this.targetClasses) {
			if(targetClass.isInstance(target)) {
				assignable = true;
				break;
			}
		}
		if(!assignable) return false;
		
		// invulnerability and invisibility check
		if((!this.ignoreInvulnerability) && (target instanceof EntityHuman) && (((EntityHuman) target).abilities.isInvulnerable || ((EntityHuman) target).isInvisible())) return false;
		
		// range check
		if(this.maximumDistanceSquared>0 && NativeInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.entity,target)>this.maximumDistanceSquared) return false;
		
		// eye contact check
		if(this.maximumNoEyeContactTicks>0 && !this.entity.getEntitySenses().canSee(target)) return false;
		
		// call target event
		final EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent(this.entity, target, reason);
		if(event.isCancelled() || event.getTarget()==null || event.getTarget()==this.entity.getBukkitEntity()) return false;
		
		// set new target!
		target = ((CraftLivingEntity) event.getTarget()).getHandle();
		this.entity.setGoalTarget(target);
		if(this.entity instanceof EntityCreature) ((EntityCreature) this.entity).target = target;
		this.mob.adjustMaximumNavigationDistance(Math.sqrt(NativeInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.entity, target) * 2));
		
		return true;
	}

	@Override
	protected boolean canContinue() {
		final EntityLiving target = this.entity.getGoalTarget();
		
		// existence checks
		if(target==null) return false;
		if(!target.isAlive()) return false;
		
		// range check
		if(this.maximumDistanceSquared>0 && NativeInterfaces.ENTITY.METHOD_GETDISTANCETOENTITYSQUARED.invoke(this.entity,target)>this.maximumDistanceSquared) return false;
		
		// eye contact check
		if(this.maximumNoEyeContactTicks>0) {
			if(this.entity.getEntitySenses().canSee(target)) this.noEyeContactTicks = 0;
			else {
				if(++this.noEyeContactTicks > this.maximumNoEyeContactTicks) return false;
			}
		}
		
		return true;
	}

	@Override
	protected void onEnd() {
		this.entity.setGoalTarget(null);
	}
	
	@Override
	protected void onStart() {}

	@Override
	protected void onTick() {}

}
