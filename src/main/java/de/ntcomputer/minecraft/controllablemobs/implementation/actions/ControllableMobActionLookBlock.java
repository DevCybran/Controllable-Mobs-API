package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import net.minecraft.server.v1_6_R3.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;

public class ControllableMobActionLookBlock extends ControllableMobActionLook {
	private final double[] values;
	private final World world;

	public ControllableMobActionLookBlock(final ControllableMobActionManager manager, final Location loc) {
		super(manager);
		if(loc==null) {
			this.values = null;
			this.world = null;
		}
		else {
			this.values = new double[] {loc.getX(), loc.getY(), loc.getZ()};
			this.world = ((CraftWorld) loc.getWorld()).getHandle();
		}
	}

	@Override
	public double getX() {
		return this.values[0];
	}

	@Override
	public double getY() {
		return this.values[1];
	}

	@Override
	public double getZ() {
		return this.values[2];
	}

	@Override
	public boolean isValid() {
		return super.isValid() && this.values!=null;
	}

	@Override
	public World getWorld() {
		return this.world;
	}

}
