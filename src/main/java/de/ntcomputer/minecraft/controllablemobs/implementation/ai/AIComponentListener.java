package de.ntcomputer.minecraft.controllablemobs.implementation.ai;

import net.minecraft.server.v1_7_R1.PathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

public interface AIComponentListener<T extends PathfinderGoal> {

	public void onAdd(CraftControllableMob<?> mob, T goal);
	public void onRemoved(CraftControllableMob<?> mob, T goal);
	
}
