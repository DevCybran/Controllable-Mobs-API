package de.ntcomputer.minecraft.controllablemobs.implementation;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ControllableMobAction;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionAttackMove;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionBase;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionCallback;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionDie;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionFollow;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionJump;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionLookBlock;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionLookEntity;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionManager;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionMove;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionTarget;
import de.ntcomputer.minecraft.controllablemobs.implementation.actions.ControllableMobActionWait;

public class CraftControllableMobActions implements ControllableMobActions {
	private CraftControllableMob<?> mob;
	private boolean defaultQueue = false;
	ControllableMobActionManager actionManager;
	
	CraftControllableMobActions(final CraftControllableMob<?> mob) {
		this.mob = mob;
		this.actionManager = new ControllableMobActionManager();
	}
	
	private ControllableMobAction addAction(final ControllableMobActionBase action, final boolean queue) {
		if(queue) this.actionManager.addActionToQueue(action);
		else this.actionManager.addActionAsRunning(action);
		return action;
	}
	
	void dispose() {
		this.actionManager.dispose();
		this.actionManager = null;
		this.mob = null;
	}
	

	@Override
	public ControllableMobAction moveTo(Location loc) {
		return this.moveTo(loc, defaultQueue);
	}

	@Override
	public ControllableMobAction moveTo(Location loc, boolean queue) {
		return this.addAction(new ControllableMobActionMove(this.actionManager, loc, 1.0D), queue);
	}

	@Override
	public ControllableMobAction lookAt(Location loc) {
		return lookAt(loc, defaultQueue);
	}

	@Override
	public ControllableMobAction lookAt(Entity entity) throws IllegalArgumentException {
		return lookAt(entity, defaultQueue);
	}

	@Override
	public ControllableMobAction lookAt(Location loc, boolean queue) {
		return this.addAction(new ControllableMobActionLookBlock(this.actionManager, loc), queue);
	}

	@Override
	public ControllableMobAction lookAt(Entity entity, boolean queue) throws IllegalArgumentException {
		return this.addAction(new ControllableMobActionLookEntity(this.mob, entity), queue);
	}

	@Override
	public ControllableMobAction target(LivingEntity entity) throws IllegalArgumentException {
		return this.target(entity, defaultQueue);
	}

	@Override
	public ControllableMobAction target(LivingEntity entity, boolean queue) throws IllegalArgumentException {
		return this.addAction(new ControllableMobActionTarget(this.mob, entity), queue);
	}

	@Override
	public ControllableMobAction wait(int serverTicks) {
		return this.wait(serverTicks, defaultQueue);
	}

	@Override
	public ControllableMobAction wait(int serverTicks, boolean queue) {
		return this.addAction(new ControllableMobActionWait(this.actionManager, serverTicks), queue);
	}

	@Override
	public void die() {
		this.die(defaultQueue);
	}

	@Override
	public ControllableMobAction die(boolean queue) {
		return this.addAction(new ControllableMobActionDie(this.mob), queue);
	}

	@Override
	public ControllableMobAction jump() {
		return this.jump(1);
	}

	@Override
	public ControllableMobAction jump(int times) {
		return this.jump(times, defaultQueue);
	}

	@Override
	public ControllableMobAction jump(boolean queue) {
		return this.jump(1, queue);
	}

	@Override
	public ControllableMobAction jump(int times, boolean queue) {
		return this.addAction(new ControllableMobActionJump(this.actionManager, times), queue);
	}

	@Override
	public ControllableMobAction follow(LivingEntity entity) throws IllegalArgumentException {
		return this.follow(entity, defaultQueue);
	}

	@Override
	public ControllableMobAction follow(LivingEntity entity, boolean queue) throws IllegalArgumentException {
		return this.follow(entity, queue, 10.0F);
	}

	@Override
	public ControllableMobAction follow(LivingEntity entity, boolean queue, float radius) throws IllegalArgumentException {
		return this.follow(entity, queue, radius, 2.0F);
	}

	@Override
	public ControllableMobAction follow(LivingEntity entity, boolean queue,	float outerRadius, float targetRadius) throws IllegalArgumentException {
		return this.addAction(new ControllableMobActionFollow(this.mob,entity,outerRadius,targetRadius), queue);
	}

	@Override
	public ControllableMobAction callback(Runnable runnable) {
		return this.addAction(new ControllableMobActionCallback(this.actionManager, runnable), true);
	}

	@Override
	public boolean isActionRunning(ActionType type) {
		return this.actionManager.isActionRunning(type);
	}

	@Override
	public void clearActionQueue() {
		this.actionManager.clearQueue();
	}

	@Override
	public void clearActionsRunning() {
		this.actionManager.clearRunning();
	}

	@Override
	public void clearActions() {
		this.clearActionQueue();
		this.clearActionsRunning();
	}

	@Override
	public boolean getDefaultQueuingFlag() {
		return this.defaultQueue;
	}

	@Override
	public void setDefaultQueuingFlag(boolean defaultQueue) {
		this.defaultQueue = defaultQueue;
	}

	@Override
	public ControllableMobAction attackMoveTo(Location loc) {
		return this.attackMoveTo(loc, defaultQueue);
	}

	@Override
	public ControllableMobAction attackMoveTo(Location loc, boolean queue) {
		return this.attackMoveTo(loc, queue, 16.0D);
	}

	@Override
	public ControllableMobAction attackMoveTo(Location loc, boolean queue, double maximumDistractionDistance) throws IllegalArgumentException {
		return this.attackMoveTo(loc, queue, maximumDistractionDistance, 1.0D);
	}

	@Override
	public ControllableMobAction attackMoveTo(Location loc, boolean queue, double maximumDistractionDistance, double movementSpeedMultiplicator) throws IllegalArgumentException {
		if(maximumDistractionDistance <= 0) throw new IllegalArgumentException("maximumDistractionDistance must be greater than 0.0");
		if(movementSpeedMultiplicator <= 0) throw new IllegalArgumentException("movementSpeedMultiplicator must be greater than 0.0");
		return this.addAction(new ControllableMobActionAttackMove(this.actionManager, loc, movementSpeedMultiplicator, maximumDistractionDistance), queue);
	}

}
