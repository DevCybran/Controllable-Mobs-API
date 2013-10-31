package de.ntcomputer.minecraft.controllablemobs.implementation.actions;

import net.minecraft.server.v1_6_R3.Entity;
import de.ntcomputer.minecraft.controllablemobs.api.actions.ActionType;
import de.ntcomputer.minecraft.controllablemobs.implementation.CraftControllableMob;

public class ControllableMobActionTeleportEntity extends ControllableMobActionBase {
	/*private static Random random = new Random();
	
	private final CraftControllableMob<?> mob;
	private final Entity entity;*/

	public ControllableMobActionTeleportEntity(CraftControllableMob<?> mob, Entity entity, float targetDistance) throws IllegalArgumentException {
		super(mob.getActionManager(), ActionType.TELEPORT);
		/*if(entity==null) throw new IllegalArgumentException("the entity must not be null");
		if(entity==mob.getEntity()) throw new IllegalArgumentException("teleporting the entity to itself is not possible");
		this.mob = mob;
		this.entity = entity;*/
	}

	/*@Override
	void start() {
		super.start();
		if(entity.isDead()) this.cancel();
		else {
			this.mob.getEntity().teleport(this.entity, TeleportCause.PLUGIN);
			this.finish();
		}
	}
	
	
	private static void getRandomPositionAroundEntity(Entity entity, int maxXZDistance, int maxYDistance) {
		Vec3D par3Vec3 = null;
        boolean var5 = false;
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;
        float var9 = -99999.0F;

        for (int var16 = 0; var16 < 10; ++var16)
        {
            int var12 = random.nextInt(2 * maxXZDistance) - maxXZDistance;
            int var17 = random.nextInt(2 * maxYDistance) - maxYDistance;
            int var14 = random.nextInt(2 * maxXZDistance) - maxXZDistance;

            if (par3Vec3 == null || (double)var12 * par3Vec3.xCoord + (double)var14 * par3Vec3.zCoord >= 0.0D)
            {
                var12 += MathHelper.floor_double(par0EntityCreature.posX);
                var17 += MathHelper.floor_double(par0EntityCreature.posY);
                var14 += MathHelper.floor_double(par0EntityCreature.posZ);

                if (!var10 || par0EntityCreature.isWithinHomeDistance(var12, var17, var14))
                {
                    float var15 = par0EntityCreature.getBlockPathWeight(var12, var17, var14);

                    if (var15 > var9)
                    {
                        var9 = var15;
                        var6 = var12;
                        var7 = var17;
                        var8 = var14;
                        var5 = true;
                    }
                }
            }
        }

        if (var5)
        {
            return par0EntityCreature.worldObj.getWorldVec3Pool().getVecFromPool((double)var6, (double)var7, (double)var8);
        }
        else
        {
            return null;
        }
    }
	}
	
	public static void teleport(Entity entity, Entity target, float targetDistance) {
		
	}*/

}
