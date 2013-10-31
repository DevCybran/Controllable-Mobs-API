package de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.classes;

import net.minecraft.server.v1_6_R3.PathfinderGoal;
import de.ntcomputer.minecraft.controllablemobs.implementation.nativeinterfaces.system.NativeMethodPublic;

public final class NotchInterfacePathfinderGoal {
	public final OnEnd METHOD_ONEND = new OnEnd();
	
	public class OnEnd extends NativeMethodPublic {
		public void invoke(PathfinderGoal goal) {
			try {
				// siehe zb PathfinderGoalMeleeAttack
				goal.d();
			} catch(Throwable e) {
				this.handleException(e);
			}
		}
	}

}
