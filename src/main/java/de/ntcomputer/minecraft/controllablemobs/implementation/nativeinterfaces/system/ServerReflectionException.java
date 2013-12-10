package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServerReflectionException extends RuntimeException {
	private static final long serialVersionUID = 84199168800291577L;
	private static final List<NativeInterface> thrown = new ArrayList<NativeInterface>();
	private static int skipped = 0;
	private static long lastSkippedMessageTime = 0;
	
	public static boolean shouldShow(NativeInterface nativeInterface) {
		if(thrown.contains(nativeInterface)) {
			return false;
		} else {
			thrown.add(nativeInterface);
			return true;
		}
	}
	
	public static void skip() {
		final long currentTime = System.currentTimeMillis();
		skipped++;
		if(lastSkippedMessageTime==0) {
			lastSkippedMessageTime = currentTime;
		} else if(currentTime > lastSkippedMessageTime+20000) {
			Logger.getLogger("Minecraft").severe("[ControllableMobsAPI] skipped "+skipped+" errors in the last " + (currentTime-lastSkippedMessageTime)/1000 + " seconds. You should stop the server and fix the problem");
			skipped = 0;
			lastSkippedMessageTime = currentTime;
		}
	}

	public ServerReflectionException(final String message, final Exception ancestor) {
		super(message,ancestor);
	}
	
	public ServerReflectionException(final String message) {
		super(message);
	}
}
