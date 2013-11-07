package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.server;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NmsInterfacePathfinderGoal {
	public final OnEnd METHOD_ONEND = new OnEnd();
	
	public class OnEnd extends NativeMethodPublic {
		public void invoke(PathfinderGoal goal) {
			try {
				// for instance PathfinderGoalMeleeAttack (this.b.getNavigation().h();)
				goal.d();
			} catch(Throwable e) {
				this.handleException(e);
			}
		}
	}

}
