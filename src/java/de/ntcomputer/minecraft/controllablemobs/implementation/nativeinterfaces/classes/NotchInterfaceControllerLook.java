package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import net.minecraft.server.v1_6_R3.ControllerLook;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public class NotchInterfaceControllerLook {
	public final Look METHOD_LOOKATCOORDINATES = new Look();
	
	public class Look extends NativeMethodPublic {
		public void invoke(final ControllerLook controller, double x, double y, double z, float horizontalFaceSpeed, float verticalFaceSpeed) {
			try {
				controller.a(x,y,z,horizontalFaceSpeed,verticalFaceSpeed);
			} catch(Throwable e) {
				this.handleException(e);
			}
		}
	}

}
