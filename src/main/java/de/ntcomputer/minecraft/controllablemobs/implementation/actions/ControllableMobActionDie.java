package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import net.minecraft.server.v1_7_R1.DamageSource;
import net.minecraft.server.v1_7_R1.EntityLiving;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

public class ControllableMobActionDie extends ControllableMobActionBase {
	private final EntityLiving entity;

	public ControllableMobActionDie(final CraftControllableMob<?> mob) {
		super(mob.getActionManager(), ActionType.DIE);
		this.entity = mob.nmsEntity;
	}

	@Override
	void start() {
		super.start();
		this.entity.damageEntity(DamageSource.GENERIC, this.entity.getHealth()+1);
		this.finish();
	}

}
