package stagger.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

public class EntryActions {
	
	public static Entity getClosestEntity() {
		EntityLivingBase closestEntity = null;
		EntityLivingBase en = null;
		for(Object o : Minecraft.getMinecraft().theWorld.loadedEntityList) {
			if (o instanceof EntityLivingBase) {
				en = (EntityLivingBase)o;
			} else {
				continue;
			}
			if(!(o instanceof EntityPlayerSP) && !en.isDead && en.getHealth() > 0 && Minecraft.getMinecraft().thePlayer.canEntityBeSeen(en)) {
				if(closestEntity == null || Minecraft.getMinecraft().thePlayer.getDistanceToEntity(en) < Minecraft.getMinecraft().thePlayer.getDistanceToEntity(closestEntity)) {
					closestEntity = en;
				}
			}
			
		}
		return closestEntity;
	}
	
	
	public synchronized static void faceEntityClient(EntityLivingBase entity) {
    	float[] rotations = getRotationsNeeded(entity);
    	if (rotations != null) {
            Minecraft.getMinecraft().thePlayer.rotationYaw = limitAngleChange(Minecraft.getMinecraft().thePlayer.prevRotationYaw, rotations[0], 55);//NoCheat+ bypass!!!
    		Minecraft.getMinecraft().thePlayer.rotationPitch = rotations[1];
    	}
    }
	
	
	public static float[] getRotationsNeeded(Entity entity) {
    	if(entity == null) {
    		return null;
    	}
        double diffX = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
        double diffZ = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double diffY;
        if(entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            diffY = entityLivingBase.posY + (double)entityLivingBase.getEyeHeight() * 0.9 - (Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight());
        } else {
            diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D - (Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight());
        }
        double dist = (double)MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0D / Math.PI));
        
        return new float[] {
        		Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw),
        		Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch)
        		};
        }
	
	
	public final static float limitAngleChange(final float current, final float intended, final float maxChange) {
		float change = intended - current;

		if(change > maxChange) {
			change = maxChange;
		} else if(change < -maxChange) {
			change = -maxChange;
		}
		return current + change;
	}


	
}
