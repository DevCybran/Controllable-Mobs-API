package de.ntcomputer.minecraft.controllablemobs.api.actions;

/**
 * Defines the type of a {@link ControllableMobAction}.
 * Can be obtained by calling {@link ControllableMobAction#getType()} 
 *  
 * @author Cybran
 * @version v2
 *
 */
public enum ActionType {
	
	/**
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#lookAt(org.bukkit.entity.Entity, boolean)
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#lookAt(org.bukkit.Location, boolean)
	 */
	LOOK(true, false),
	
	/**
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#moveTo(org.bukkit.Location, boolean)
	 */
	MOVE(false, false),
	
	/**
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#target(org.bukkit.entity.LivingEntity, boolean)
	 */
	TARGET(false, false),
	
	/**
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#wait(int, boolean)
	 */
	WAIT(false, false),
	
	/**
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#die(boolean)
	 */
	DIE(false, false),
	
	/**
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#jump(int, boolean)
	 */
	JUMP(false, true),
	
	/**
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#callback(Runnable)
	 */
	CALLBACK(false, false),
	
	/**
	 * @see de.ntcomputer.minecraft.controllablemobs.api.ControllableMobActions#follow(org.bukkit.entity.LivingEntity, boolean, float, float)
	 */
	FOLLOW(true, true),
	
	TELEPORT(false, false);
	
	
	public final boolean isBackgroundTask;
	public final boolean isMultiCycleTask;
	
	private ActionType(final boolean isBackgroundTask, final boolean isMultiCycleTask) {
		this.isBackgroundTask = isBackgroundTask;
		this.isMultiCycleTask = isMultiCycleTask;
	}
	
	
}
