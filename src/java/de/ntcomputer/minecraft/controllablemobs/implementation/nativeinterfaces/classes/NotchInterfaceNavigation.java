package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.Navigation;
import net.minecraft.server.v1_6_R3.PathEntity;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public class NotchInterfaceNavigation {
	// set in constructor, 3rd argument for world.findPath
	//public final NativeFieldFloat<Navigation> FIELD_MAXPATHLENGTH = new NativeFieldFloat<Navigation>(Navigation.class, "e");
	public final IsMoveFinished METHOD_ISNOTMOVING = new IsMoveFinished();
	public final StopMove METHOD_STOP = new StopMove();
	public final MoveToEntity METHOD_MOVETOENTITY = new MoveToEntity();
	public final MoveAlongPath METHOD_MOVEALONGPATH = new MoveAlongPath();
	public final CreatePathToEntity METHOD_CREATEPATHTOENTITY = new CreatePathToEntity();
	public final CreatePathToLocation METHOD_CREATEPATHTOLOCATION = new CreatePathToLocation();

	public class IsMoveFinished extends NativeMethodPublic {
		public boolean invoke(final Navigation navigation) {
			try {
				// return (this.c == null) || (this.c.b());
				return navigation.g();
			} catch(Throwable e) {
				this.handleException(e);
				return true;
			}
		}
	}
	
	public class StopMove extends NativeMethodPublic {
		public void invoke(final Navigation navigation) {
			try {
				// this.c = null;
				navigation.h();
			} catch(Throwable e) {
				this.handleException(e);
			}
		}
	}
	
	public class MoveToEntity extends NativeMethodPublic {
		public void invoke(final Navigation navigation, EntityLiving entity, double movementSpeedMultiplicator) {
			try {
				navigation.a(entity, movementSpeedMultiplicator);
			} catch(Throwable e) {
				this.handleException(e);
			}
		}
	}
	
	public class MoveAlongPath extends NativeMethodPublic {
		public void invoke(final Navigation navigation, PathEntity path, double movementSpeedMultiplicator) {
			try {
				navigation.a(path, movementSpeedMultiplicator);
			} catch(Throwable e) {
				this.handleException(e);
			}
		}
	}
	
	public class CreatePathToEntity extends NativeMethodPublic {
		public PathEntity invoke(final Navigation navigation, final EntityLiving entity) {
			try {
				return navigation.a(entity);
			} catch(Throwable e) {
				this.handleException(e);
				return null;
			}
		}
	}
	
	public class CreatePathToLocation extends NativeMethodPublic {
		public PathEntity invoke(final Navigation navigation, final double x, final double y, final double z) {
			try {
				return navigation.a(x,y,z);
			} catch(Throwable e) {
				this.handleException(e);
				return null;
			}
		}
	}
}
