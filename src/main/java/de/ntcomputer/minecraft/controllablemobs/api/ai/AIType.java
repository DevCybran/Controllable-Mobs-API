package de.ntcomputer.minecraft.controllablemobs.api.ai;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_7_R1.PathfinderGoal;
import net.minecraft.server.v1_7_R1.PathfinderGoalArrowAttack;
import net.minecraft.server.v1_7_R1.PathfinderGoalAvoidPlayer;
import net.minecraft.server.v1_7_R1.PathfinderGoalBeg;
import net.minecraft.server.v1_7_R1.PathfinderGoalBreakDoor;
import net.minecraft.server.v1_7_R1.PathfinderGoalBreed;
import net.minecraft.server.v1_7_R1.PathfinderGoalDefendVillage;
import net.minecraft.server.v1_7_R1.PathfinderGoalEatTile;
import net.minecraft.server.v1_7_R1.PathfinderGoalFleeSun;
import net.minecraft.server.v1_7_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_7_R1.PathfinderGoalFollowOwner;
import net.minecraft.server.v1_7_R1.PathfinderGoalFollowParent;
import net.minecraft.server.v1_7_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalInteract;
import net.minecraft.server.v1_7_R1.PathfinderGoalJumpOnBlock;
import net.minecraft.server.v1_7_R1.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_7_R1.PathfinderGoalLookAtTradingPlayer;
import net.minecraft.server.v1_7_R1.PathfinderGoalMakeLove;
import net.minecraft.server.v1_7_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_7_R1.PathfinderGoalMoveIndoors;
import net.minecraft.server.v1_7_R1.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_7_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_7_R1.PathfinderGoalMoveTowardsTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalOcelotAttack;
import net.minecraft.server.v1_7_R1.PathfinderGoalOfferFlower;
import net.minecraft.server.v1_7_R1.PathfinderGoalOpenDoor;
import net.minecraft.server.v1_7_R1.PathfinderGoalOwnerHurtByTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalOwnerHurtTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalPanic;
import net.minecraft.server.v1_7_R1.PathfinderGoalPassengerCarrotStick;
import net.minecraft.server.v1_7_R1.PathfinderGoalPlay;
import net.minecraft.server.v1_7_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_7_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_7_R1.PathfinderGoalRandomTargetNonTamed;
import net.minecraft.server.v1_7_R1.PathfinderGoalRestrictOpenDoor;
import net.minecraft.server.v1_7_R1.PathfinderGoalRestrictSun;
import net.minecraft.server.v1_7_R1.PathfinderGoalSit;
import net.minecraft.server.v1_7_R1.PathfinderGoalSwell;
import net.minecraft.server.v1_7_R1.PathfinderGoalTakeFlower;
import net.minecraft.server.v1_7_R1.PathfinderGoalTame;
import net.minecraft.server.v1_7_R1.PathfinderGoalTempt;
import net.minecraft.server.v1_7_R1.PathfinderGoalTradeWithPlayer;

/**
 * An enum that lists all AI components built into the minecraft server core.<br>
 * You can use it to specifically remove default AI parts by {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMobAI#remove(AIType...)} or {@link de.ntcomputer.minecraft.controllablemobs.api.ControllableMobAI#removeExcept(AIType...)}.<br>
 * It can be also used to identify AI parts on your own by using {@link AIPart#getType()}.
 * 
 * @author Cybran
 * @version v6
 *
 */
public enum AIType {
	ATTACK_RANGED(PathfinderGoalArrowAttack.class),
	MOVE_AVOIDPLAYER(PathfinderGoalAvoidPlayer.class),
	ACTION_BEG(PathfinderGoalBeg.class),
	ACTION_DOORBREAK(PathfinderGoalBreakDoor.class),
	ACTION_BREED(PathfinderGoalBreed.class),
	ACTION_DEFENDVILLAGE(PathfinderGoalDefendVillage.class),
	ACTION_EATTILE(PathfinderGoalEatTile.class),
	MOVE_FLEESUN(PathfinderGoalFleeSun.class),
	MOVE_SWIM(PathfinderGoalFloat.class),
	MOVE_FOLLOWONWNER(PathfinderGoalFollowOwner.class),
	MOVE_FOLLOWPARENT(PathfinderGoalFollowParent.class),
	TARGET_HURTBY(PathfinderGoalHurtByTarget.class),
	ACTION_ENTITYINTERACT(PathfinderGoalInteract.class),
	MOVE_JUMPONBLOCK(PathfinderGoalJumpOnBlock.class),
	ATTACK_LEAP(PathfinderGoalLeapAtTarget.class),
	ACTION_ENTITYLOOK(PathfinderGoalLookAtPlayer.class),
	ACTION_PLAYERTRADINGLOOK(PathfinderGoalLookAtTradingPlayer.class),
	ACTION_LOVE(PathfinderGoalMakeLove.class),
	ATTACK_MELEE(PathfinderGoalMeleeAttack.class),
	MOVE_INDOORS(PathfinderGoalMoveIndoors.class),
	MOVE_VILLAGE(PathfinderGoalMoveThroughVillage.class),
	MOVE_RESTRICTED(PathfinderGoalMoveTowardsRestriction.class),
	MOVE_TARGET(PathfinderGoalMoveTowardsTarget.class),
	TARGET_NEAREST(PathfinderGoalNearestAttackableTarget.class),
	ATTACK_OCELOT(PathfinderGoalOcelotAttack.class),
	ACTION_FLOWEROFFER(PathfinderGoalOfferFlower.class),
	ACTION_DOOROPEN(PathfinderGoalOpenDoor.class),
	TARGET_OWNERATTACKER(PathfinderGoalOwnerHurtByTarget.class),
	TARGET_OWNERTARGET(PathfinderGoalOwnerHurtTarget.class),
	MOVE_PANIC(PathfinderGoalPanic.class),
	MOVE_FOLLOWCARROT(PathfinderGoalPassengerCarrotStick.class),
	ACTION_PLAY(PathfinderGoalPlay.class),
	ACTION_RANDOMLOOK(PathfinderGoalRandomLookaround.class),
	MOVE_RANDOMSTROLL(PathfinderGoalRandomStroll.class),
	TARGET_NEARESTNONTAMED(PathfinderGoalRandomTargetNonTamed.class),
	MOVE_RESTRICTOPENDOOR(PathfinderGoalRestrictOpenDoor.class),
	MOVE_RESTRICTSUN(PathfinderGoalRestrictSun.class),
	ACTION_SIT(PathfinderGoalSit.class),
	ATTACK_SWELL(PathfinderGoalSwell.class),
	ACTION_FLOWERTAKE(PathfinderGoalTakeFlower.class),
	ACTION_TAME(PathfinderGoalTame.class),
	ACTION_TEMPT(PathfinderGoalTempt.class),
	ACTION_PLAYERTRADE(PathfinderGoalTradeWithPlayer.class),
	UNKNOWN(null);
	
	private final Class<? extends PathfinderGoal> nmsPathfinderClass;
	
	private AIType(Class<? extends PathfinderGoal> pfgClass) {
		this.nmsPathfinderClass = pfgClass;
	}
	
	
	private static final Map<Class<? extends PathfinderGoal>,AIType> classTypeMap;
	
	static {
		classTypeMap = new HashMap<Class<? extends PathfinderGoal>,AIType>();
		for(AIType type: AIType.values()) {
			if(type!=AIType.UNKNOWN) classTypeMap.put(type.nmsPathfinderClass, type);
		}
	}
	
	/**
	 * You won't need this. But the API implementation will.
	 */
	public static AIType getTypeByInstance(PathfinderGoal goal) {
		if(classTypeMap.containsKey(goal.getClass())) return classTypeMap.get(goal.getClass());
		else return AIType.UNKNOWN;
	}

}
