package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_7_R4.Entity;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public class NmsEntity {
	public final GetEntityDistanceSquared METHOD_GETDISTANCETOENTITYSQUARED = new GetEntityDistanceSquared();
	public final GetLocationDistance METHOD_GETDISTANCETOLOCATION = new GetLocationDistance();
	public final GetLocationDistanceSquared METHOD_GETDISTANCETOLOCATIONSQUARED = new GetLocationDistanceSquared();
	
	public class GetEntityDistanceSquared extends NativeMethodPublic {
		public double invoke(final Entity entity, final Entity target) {
			try {
				// method returns difLocX² + difLocY² + difLocZ² for entity
				return entity.e(target);
			} catch(Throwable e) {
				this.handleException(e);
				return 0;
			}
		}
	}
	
	public class GetLocationDistance extends NativeMethodPublic {
		public double invoke(final Entity entity, final double x, final double y, final double z) {
			try {
				// method returns Math.sqrt( difLocX² + difLocY² + difLocZ² ) for double values
				return entity.f(x, y, z);
			} catch(Throwable e) {
				this.handleException(e);
				return 0;
			}
		}
	}
	
	public class GetLocationDistanceSquared extends NativeMethodPublic {
		public double invoke(final Entity entity, final double x, final double y, final double z) {
			try {
				// method returns difLocX² + difLocY² + difLocZ² for double values
				return entity.e(x, y, z);
			} catch(Throwable e) {
				this.handleException(e);
				return 0;
			}
		}
	}

}
