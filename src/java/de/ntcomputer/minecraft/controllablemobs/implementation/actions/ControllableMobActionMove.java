package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import net.minecraft.server.v1_6_R3.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;

import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;

public class ControllableMobActionMove extends ControllableMobActionBase {
	public final int x;
	public final int y;
	public final int z;
	public final World world;
	private final boolean isValid;
	
	public ControllableMobActionMove(final ControllableMobActionManager manager, final Location to) {
		super(manager, ActionType.MOVE);
		if(to==null) {
			this.x = 0;
			this.y = 0;
			this.z = 0;
			this.world = null;
			this.isValid = false;
		} else {
			this.x = to.getBlockX();
			this.y = to.getBlockY();
			this.z = to.getBlockZ();
			this.world = ((CraftWorld) to.getWorld()).getHandle();
			this.isValid = true;
		}
	}

	@Override
	public boolean isValid() {
		return super.isValid() && this.isValid;
	}

}
