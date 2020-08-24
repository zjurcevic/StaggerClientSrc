package stagger.mods;

import org.lwjgl.input.Keyboard;

import stagger.main.Category;

public class Flight extends Module {

	public Flight() {
		super("Flight", Keyboard.KEY_F, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.thePlayer.capabilities.isFlying = true;
			
			if(mc.gameSettings.keyBindJump.isPressed()) {
				mc.thePlayer.motionY += 0.2;
			}
			if(mc.gameSettings.keyBindSneak.isPressed()) {
				mc.thePlayer.motionY -= 0.2;
			}
			if(mc.gameSettings.keyBindForward.isPressed()) {
				mc.thePlayer.capabilities.setFlySpeed(0.1F);
			}
		}
	}
	
	@Override
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
	}
	
}
