package de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget.DistanceComparator;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityLiving;

import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

import com.google.common.base.Predicate;

import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.NativeInterfaces;

public class PathfinderGoalTargetNearest extends PathfinderGoalTargetEx {
	private final double searchDistance;
	private final DistanceComparator comparator;
	private final Predicate<Entity> entitySelector;

	public PathfinderGoalTargetNearest(CraftControllableMob<?> mob, int maximumNoEyeContactTicks, boolean ignoreInvulnerability, double maximumDistance, Class<? extends EntityLiving>[] targetClasses, Predicate<Entity> entitySelector) {
		super(mob, maximumNoEyeContactTicks, ignoreInvulnerability, maximumDistance, targetClasses);
		this.searchDistance = maximumDistance<=0 ? 500 : maximumDistance;
		this.comparator = new DistanceComparator(entity);
		this.entitySelector = entitySelector;
	}

	@Override
	protected boolean canStart() {
		final List<EntityLiving> entities = new ArrayList<EntityLiving>();
		
		for(Class<? extends EntityLiving> targetClass: this.targetClasses) {
			if(targetClass==EntityHuman.class) {
				this.findNearbyPlayersOptimized(this.entity, entities);
			} else {
				entities.addAll(NativeInterfaces.WORLD.METHOD_SEARCHENTITIES.invoke(this.entity.world, targetClass, this.entity.getBoundingBox().grow(this.searchDistance, this.searchDistance/4.0, this.searchDistance), this.entitySelector));
			}
		}
		
		if(entities.size()>1) Collections.sort(entities, this.comparator);
		
		for(EntityLiving possibleTarget: entities) {
			if(this.target(possibleTarget, TargetReason.CLOSEST_PLAYER)) return true;
		}
		
		return false;
	}
	
	private void findNearbyPlayersOptimized(final EntityLiving entity, final List<EntityLiving> targets) {
		for(int i=0; i<entity.world.players.size(); i++) {
			EntityHuman human = (EntityHuman) entity.world.players.get(i);
			if(human!=null && !human.dead) {
				if(this.entitySelector==null) {
					targets.add(human);
				}
			}
		}
	}

}
