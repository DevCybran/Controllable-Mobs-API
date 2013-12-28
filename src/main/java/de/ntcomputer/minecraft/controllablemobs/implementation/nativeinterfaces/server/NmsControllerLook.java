package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_7_R1.ControllerLook;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public class NmsControllerLook {
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
