package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system;



public abstract class NativeInterface {
	
	protected void handleException(final Throwable t) {
		if(ServerReflectionException.shouldShow(this)) {
			ServerReflectionException e;
			if(t instanceof Exception) {
				e = new ServerReflectionException(
						"[ControllableMobsAPI] detected an incompatibility between the API and this CraftBukkit version. " +
								"Visit http://dev.bukkit.org/server-mods/controllable-mobs-api/ for more information. " +
								"+++ "+this.getClass().getName()+": "+this.getParameters()+" +++"
							, (Exception) t);
				e.printStackTrace();
			} else {
				e = new ServerReflectionException(
						"[ControllableMobsAPI] detected an incompatibility between the API and this CraftBukkit version. " +
								"Visit http://dev.bukkit.org/server-mods/controllable-mobs-api/ for more information. " +
								"+++ "+this.getClass().getName()+": "+this.getParameters()+" +++");
				e.printStackTrace();
				t.printStackTrace();
			}
		} else {
			ServerReflectionException.skip();
		}
	}
	
	protected abstract String getParameters();

}
