package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import net.minecraft.server.v1_6_R3.EntityLiving;

import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

public class ControllableMobActionFollow extends ControllableMobActionBase {
	public final EntityLiving target;
	public final float maximumDistanceSquared;
	public final float minimumDistanceSquared;
	
	public ControllableMobActionFollow(final CraftControllableMob<?> mob, final LivingEntity target, final float maximumDistance, final float minimumDistance) throws IllegalArgumentException {
		super(mob.getActionManager(), ActionType.FOLLOW);
		if(mob.getEntity()==target) throw new IllegalArgumentException("[ControllableMobsAPI] Following the entity itself is not allowed");
		this.maximumDistanceSquared = maximumDistance * maximumDistance;
		this.minimumDistanceSquared = minimumDistance * minimumDistance;
		if(this.minimumDistanceSquared >= this.maximumDistanceSquared) throw new IllegalArgumentException("[ControllableMobsAPI] the target radius must not be smaller than the outer radius when following an entity");
		this.target = target==null ? null : ((CraftLivingEntity) target).getHandle();
	}
	
	@Override
	public boolean isValid() {
		return super.isValid() && this.target!=null && this.target.isAlive();
	}

}
