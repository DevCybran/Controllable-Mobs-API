package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.IEntitySelector;
import de.ntcomputer.minecraft.controllablemobs.api.ai.EntityFilter;

public final class EntitySelector implements IEntitySelector {
	private final EntityFilter filter;
	
	public EntitySelector(final EntityFilter filter) {
		this.filter = filter;
	}

	@Override
	public boolean a(Entity entity) {
		return this.isEntityValid(entity);
	}

	public boolean isEntityValid(Entity entity) {
		return this.filter.isEntityValid(entity.getBukkitEntity());
	}

}
