package stagger.mods;

import org.apache.http.util.EntityUtils;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import stagger.main.Category;
import stagger.utils.EntryActions;

public class FightBot extends Module{

	public FightBot() {
		super("FightBot", Keyboard.KEY_B, Category.COMBAT);
	}
	
	private float speed = 9.9F; //set to 101ms for best chance in avoiding anti-cheat detection system
	private float range = 6F;
	private double distance = 3D;
	private EntityLivingBase entity;
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			getBestWeapon();
			if(EntryActions.getClosestEntity() != null && EntryActions.getClosestEntity() instanceof EntityLivingBase) {
				entity = (EntityLivingBase)EntryActions.getClosestEntity();
			}
			if(entity == null) {
				return;
			}
			if(entity.getHealth() <= 0 || entity.isDead || mc.thePlayer.getHealth() <= 0) {
				entity = null;
				mc.gameSettings.keyBindForward.pressed = false;
				return;
			}
			double xDist = Math.abs(mc.thePlayer.posX - entity.posX);
			double zDist = Math.abs(mc.thePlayer.posZ - entity.posZ);
			EntryActions.faceEntityClient(entity);
			if(xDist > distance || zDist > distance) {
				mc.gameSettings.keyBindForward.pressed = true;
			} else {
				mc.gameSettings.keyBindForward.pressed = false;
			}
			if(mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround) {
				mc.thePlayer.jump();
			}
			if (mc.thePlayer.isInWater() && mc.thePlayer.posY < entity.posY) {
				mc.thePlayer.motionY += 0.04;
			}

			updateMS();
			if(hasTimePassed(speed)) {
				if(mc.thePlayer.getDistanceToEntity(entity) <= range) {
					if(getDistanceFromMouse(entity) <= 55) {
						EntryActions.faceEntityClient(entity);
						mc.thePlayer.swingItem();
						mc.playerController.attackEntity(mc.thePlayer, entity);
					} else {
						EntryActions.faceEntityClient(entity);
					}
					updateLastMS();
				}
			}
		}
			
	}
	
	@Override
	public void onDisable() {
		mc.gameSettings.keyBindForward.pressed = false;
	}
	
	
	public int getDistanceFromMouse(EntityLivingBase entity){
		float[] neededRotations = EntryActions.getRotationsNeeded(entity);
		if (neededRotations != null) {
			float neededYaw = mc.thePlayer.rotationYaw - neededRotations[0];
			float neededPitch = mc.thePlayer.rotationPitch - neededRotations[1];
			float distanceFromMouse = (float) Math.sqrt(neededYaw * neededYaw + neededPitch * neededPitch);
			return (int) distanceFromMouse;
		}
		return -1;
	}
	
	
	public void getBestWeapon(){
		float damageModifier = 0;
		int newItem = -1;
		for (int slot = 0; slot < 9; slot++) {
			ItemStack stack = mc.thePlayer.inventory.mainInventory[slot];
			if(stack == null) {
				continue;
			}
			if(stack.getItem() instanceof ItemSword) {
				ItemSword is = (ItemSword)stack.getItem();
				float damage = is.getDamageVsEntity();
				if(damage >= damageModifier) {
					newItem = slot;
					damageModifier = damage;
				}
			}
		}
		if (newItem > -1) { 
			mc.thePlayer.inventory.currentItem = newItem; 
		}
	}
	
	
}
