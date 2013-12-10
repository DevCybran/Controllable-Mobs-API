package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import net.minecraft.server.v1_6_R3.EntityLiving;

import org.bukkit.craftbukkit.v1_6_R3.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

public class ControllableMobActionTarget extends ControllableMobActionBase {
	public final EntityLiving target;
	
	public ControllableMobActionTarget(final CraftControllableMob<?> mob, final LivingEntity target) throws IllegalArgumentException {
		super(mob.getActionManager(), ActionType.TARGET);
		if(mob.getEntity()==target) throw new IllegalArgumentException("[ControllableMobsAPI] Targeting the entity itself is not allowed");
		this.target = target==null ? null : ((CraftLivingEntity) target).getHandle();
	}
	
	@Override
	public boolean isValid() {
		return super.isValid() && (this.target==null || this.target.isAlive());
	}

}
