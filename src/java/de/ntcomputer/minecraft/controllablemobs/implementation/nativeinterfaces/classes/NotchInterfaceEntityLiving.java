package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import net.minecraft.server.v1_6_R2.EntityLiving;
import net.minecraft.server.v1_6_R2.PathfinderGoalSelector;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldFloat;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public class NotchInterfaceEntityLiving {
	// protected, set in subclass constructor (like this.bH = 0.25F;) - see EntitySkeleton, default is 0.7F
	public final NativeFieldFloat<EntityLiving> FIELD_MOVEMENTSPEED = new NativeFieldFloat<EntityLiving>(EntityLiving.class, "bI");
	public final NativeFieldObject<EntityLiving,PathfinderGoalSelector> FIELD_GOALSELECTOR = new NativeFieldObject<EntityLiving,PathfinderGoalSelector>(EntityLiving.class, "goalSelector");
	public final NativeFieldObject<EntityLiving,PathfinderGoalSelector> FIELD_TARGETSELECTOR = new NativeFieldObject<EntityLiving,PathfinderGoalSelector>(EntityLiving.class, "targetSelector");
	public final GetVerticalHeadSpeed METHOD_GETVERTICALHEADSPEED = new GetVerticalHeadSpeed();
	
	public final class GetVerticalHeadSpeed extends NativeMethodPublic {
		public int invoke(final EntityLiving entity) {
			try {
				return entity.bs();
			} catch(Throwable e) {
				this.handleException(e);
				return 40;
			}
		}
	}
	
}
