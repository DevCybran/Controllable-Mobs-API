package de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors;

import net.minecraft.server.v1_6_R3.PathfinderGoal;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;

import de.ntcomputer.minecraft.controllablemobs.api.ai.AIType;
import de.ntcomputer.minecraft.controllablemobs.api.ai.EntityFilter;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.EntitySelector;
import de.ntcomputer.minecraft.controllablemobs.implementation.ai.behaviors.PathfinderGoalTargetNearest;

/**
 * Target behavior: If this AIBehavior is added, the entity will search in its surroundings for an attackable target.
 * You can adjust which entities should be attacked and which prerequisites should be fulfilled for a successful targeting.
 * 
 * @author Cybran
 * @version v4
 *
 */
public class AITargetNearest extends AITargetBehaviorEx {
	private final EntitySelector entitySelector;
	
	/**
	 * Create with an automatically given priority.
	 * 
	 * @see AITargetNearest#AITargetNearest(int, double, boolean, int, EntityFilter, Class...)
	 */
	public AITargetNearest() {
		this(0);
	}
	
	/**
	 * Create with a custom priority.
	 * 
	 * @see AITargetNearest#AITargetNearest(int, double, boolean, int, EntityFilter, Class...)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 */
	public AITargetNearest(int priority) {
		this(priority, 16.0D);
	}
	
	/**
	 * Create with a custom priority and max range.
	 * 
	 * @see AITargetNearest#AITargetNearest(int, double, boolean, int, EntityFilter, Class...)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param maximumDistance the maximum distance to search for enemies, in blocks. Default value is 16.0 blocks. If 0 is specified, the range is unlimited
	 */
	public AITargetNearest(int priority, double maximumDistance) {
		this(priority, maximumDistance, false);
	}
	
	/**
	 * Create with a custom priority and max range, specify invulnerability setting.
	 * 
	 * @see AITargetNearest#AITargetNearest(int, double, boolean, int, EntityFilter, Class...)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param maximumDistance the maximum distance to search for enemies, in blocks. Default value is 16.0 blocks. If 0 is specified, the range is unlimited
	 * @param ignoreInvulnerability if set to false, the entity will only attack vulnerable and visible targets. Default is false
	 */
	public AITargetNearest(int priority, double maximumDistance, boolean ignoreInvulnerability) {
		this(priority, maximumDistance, ignoreInvulnerability, 60);
	}
	
	/**
	 * Create with a custom priority and max range, specify more preconditions.
	 * 
	 * @see AITargetNearest#AITargetNearest(int, double, boolean, int, EntityFilter, Class...)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param maximumDistance the maximum distance to search for enemies, in blocks. Default value is 16.0 blocks. If 0 is specified, the range is unlimited
	 * @param ignoreInvulnerability if set to false, the entity will only attack vulnerable and visible targets. Default is false
	 * @param maximumNoEyeContactTicks If set to a value greater than 0, the entity must have eye contact to its target to start attacking it. The value also describes, after how many server ticks without eye contact the entity will lose its current target. Default value is 60 = 3 seconds to lose its target, when the entity doesn't see it. If you specify 0, eye contact will be totally unnecessary
	 */
	public AITargetNearest(int priority, double maximumDistance, boolean ignoreInvulnerability, int maximumNoEyeContactTicks) {
		this(priority, maximumDistance, ignoreInvulnerability, maximumNoEyeContactTicks, null);
	}
	
	/**
	 * Create with a custom priority and max range, specify more preconditions and provide entity filtering.
	 * 
	 * @see AITargetNearest#AITargetNearest(int, double, boolean, int, EntityFilter, Class...)
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param maximumDistance the maximum distance to search for enemies, in blocks. Default value is 16.0 blocks. If 0 is specified, the range is unlimited
	 * @param ignoreInvulnerability if set to false, the entity will only attack vulnerable and visible targets. Default is false
	 * @param maximumNoEyeContactTicks If set to a value greater than 0, the entity must have eye contact to its target to start attacking it. The value also describes, after how many server ticks without eye contact the entity will lose its current target. Default value is 60 = 3 seconds to lose its target, when the entity doesn't see it. If you specify 0, eye contact will be totally unnecessary
	 * @param filter an EntityFilter to select only custom entities in range. If set to null, this argument is ignored
	 */
	@SuppressWarnings("unchecked")
	public AITargetNearest(int priority, double maximumDistance, boolean ignoreInvulnerability, int maximumNoEyeContactTicks, EntityFilter filter) {
		this(priority, maximumDistance, ignoreInvulnerability, maximumNoEyeContactTicks, filter, HumanEntity.class);
	}
	
	/**
	 * Create with the ultimate filtering settings!
	 * 
	 * @param priority the priority of this behavior. Specify 0 to auto-generate it
	 * @param maximumDistance the maximum distance to search for enemies, in blocks. Default value is 16.0 blocks. If 0 is specified, the range is unlimited
	 * @param ignoreInvulnerability if set to false, the entity will only attack vulnerable and visible targets. Default is false
	 * @param maximumNoEyeContactTicks If set to a value greater than 0, the entity must have eye contact to its target to start attacking it. The value also describes, after how many server ticks without eye contact the entity will lose its current target. Default value is 60 = 3 seconds to lose its target, when the entity doesn't see it. If you specify 0, eye contact will be totally unnecessary
	 * @param filter an EntityFilter to select only custom entities in range. If set to null, this argument is ignored
	 * @param targetClasses Only accept entities with any of the given classes as targets. Default value is [HumanEntity.class]. HumanEntity.class or Player.class handling performance is significantly optimized
	 * @throws IllegalArgumentException when targetClasses is specified as null or an empty array or it contains null.
	 */
	public AITargetNearest(int priority, double maximumDistance, boolean ignoreInvulnerability, int maximumNoEyeContactTicks, EntityFilter filter, Class<? extends LivingEntity>... targetClasses) throws IllegalArgumentException {
		super(priority, maximumNoEyeContactTicks, ignoreInvulnerability, maximumDistance, targetClasses);
		this.entitySelector = filter==null ? null : new EntitySelector(filter);
	}

	@Override
	public PathfinderGoal createPathfinderGoal(CraftControllableMob<?> mob) {
		return new PathfinderGoalTargetNearest(mob, this.maximumNoEyeContactTicks, this.ignoreInvulnerability, this.maximumDistance, this.targetClasses, this.entitySelector);
	}

	@Override
	public AIType getType() {
		return AIType.TARGET_NEAREST;
	}

}
