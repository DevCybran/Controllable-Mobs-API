package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_7_R1.ControllerJump;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public class NmsControllerJump {
	public final Jump METHOD_JUMP = new Jump();
	
	public class Jump extends NativeMethodPublic {
		public void invoke(final ControllerJump controller) {
			try {
				controller.a();
			} catch(Throwable e) {
				this.handleException(e);
			}
		}
	}

}
