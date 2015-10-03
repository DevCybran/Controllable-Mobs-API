package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.primitives.NativeFieldObject;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NmsEntityInsentient {
	public final NativeFieldObject<EntityInsentient,PathfinderGoalSelector> FIELD_GOALSELECTOR = new NativeFieldObject<EntityInsentient,PathfinderGoalSelector>(EntityInsentient.class, "goalSelector");
	public final NativeFieldObject<EntityInsentient,PathfinderGoalSelector> FIELD_TARGETSELECTOR = new NativeFieldObject<EntityInsentient,PathfinderGoalSelector>(EntityInsentient.class, "targetSelector");
	public final GetVerticalHeadSpeed METHOD_GETVERTICALHEADSPEED = new GetVerticalHeadSpeed();
	
	public final class GetVerticalHeadSpeed extends NativeMethodPublic {
		public int invoke(EntityInsentient entity) {
			try {
				//TODO TROVARE return entity.x();
				return 40;
			} catch(Throwable e) {
				this.handleException(e);
				return 40;
			}
		}
	}
	
}
