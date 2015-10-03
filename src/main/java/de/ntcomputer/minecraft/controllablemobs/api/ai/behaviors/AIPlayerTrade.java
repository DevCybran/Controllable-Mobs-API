package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import net.minecraft.server.v1_8_R3.PathfinderGoalTradeWithPlayer;

import org.bukkit.entity.Villager;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

/**
 * This AI behavior will mark a villager as trading with a player.
 * 
 * @author DevCybran
 * @version 1.7.2.6
 *
 */
public final class AIPlayerTrade extends AIBehavior<Villager> {

	public AIPlayerTrade(int priority) {
		super(priority);
	}

	@Override
	public AIType getType() {
		return AIType.ACTION_PLAYERTRADE;
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<? extends Villager> mob) {
		return new PathfinderGoalTradeWithPlayer((EntityVillager) mob.nmsEntity);
	}

}
