package stagger.mods;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import stagger.main.Category;

public class WallClimb extends Module{

	public WallClimb() {
		super("WallClimb", Keyboard.CHAR_NONE, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.thePlayer.isCollidedHorizontally){
				mc.thePlayer.motionY = 0.2;
			}
		}
	}
	
}
