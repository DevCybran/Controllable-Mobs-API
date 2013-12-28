package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_7_R1.EntityVillager;
import net.minecraft.server.v1_7_R1.PathfinderGoal;
import net.minecraft.server.v1_7_R1.PathfinderGoalLookAtTradingPlayer;

import org.bukkit.entity.Villager;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * This AI behavior will make a villager look at the player he is trading with (if any).
 * 
 * @author DevCybran
 * @version 1.7.2.6
 *
 */
public final class AIPlayerTradingLook extends AIBehavior<Villager> {

	public AIPlayerTradingLook(int priority) {
		super(priority);
	}

	@Override
	public AIType getType() {
		return AIType.ACTION_PLAYERTRADINGLOOK;
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends Villager> mob) {
		return new PathfinderGoalLookAtTradingPlayer((EntityVillager) mob.nmsEntity);
	}

}
