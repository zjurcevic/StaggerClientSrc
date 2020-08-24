package stagger.mods;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import stagger.main.Category;
import stagger.utils.EntryActions;
import stagger.utils.RenderMobBox;

public class BowAimbot extends Module {

	public BowAimbot() {
		super("BowAimbot", Keyboard.KEY_V, Category.COMBAT);
	}
	
	private Entity target;
	
	@Override
	public void onRender() {
		if(this.isToggled() && target != null) {
			RenderMobBox.mobBox(target, 3);
		}
	}
	
	@Override
	public void onUpdate(){
		if(this.isToggled()) {
			target = null;
			if(mc.thePlayer.inventory.getCurrentItem() != null
					&& mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemBow
					&& mc.gameSettings.keyBindUseItem.pressed) {
				
				target = EntryActions.getClosestEntity();
				aimAtTarget(target);
			}
		}
	}
	

	private void aimAtTarget(Entity target){
		if(target == null) {
			return;
		}
		int bowCharge = mc.thePlayer.getItemInUseDuration();
		int maxCharge = bowCharge / 20;
		
		if(maxCharge < 0.1) {
			if(target instanceof EntityLivingBase) {
				EntryActions.faceEntityClient((EntityLivingBase) target);
			}
			return;
		}
		
		float velocity = 1F;
		float g = 0.006F;
		double posX = target.posX - mc.thePlayer.posX;
		double posY = target.posY + target.getEyeHeight() - 0.15 - mc.thePlayer.posY - mc.thePlayer.getEyeHeight();
		double posZ = target.posZ - mc.thePlayer.posZ;
		float yaw = (float) Math.toDegrees(Math.atan2(posZ, posX)) - 90;
		double d = Math.sqrt(posX * posX + posZ * posZ);
		float tmp = (float) (velocity * velocity * velocity * velocity - g * (g * (d * d) + 2 * posY * (velocity * velocity)));
		float pitch = (float) -Math.toDegrees(Math.atan((velocity * velocity - Math.sqrt(tmp)) / (g * d)));
		mc.thePlayer.rotationYaw = yaw;
		mc.thePlayer.rotationPitch = pitch;
	}

}
