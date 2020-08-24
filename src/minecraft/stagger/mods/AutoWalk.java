package stagger.mods;

import org.lwjgl.input.Keyboard;

import stagger.main.Category;

public class AutoWalk extends Module {

	public AutoWalk() {
		super("AutoWalk", Keyboard.CHAR_NONE, Category.PLAYER);
	}
	
	@Override
	public void onUpdate()
	{
		if (this.isToggled()) {
			mc.gameSettings.keyBindForward.pressed = true;
		}
	}
	
	@Override
	public void onDisable()
	{
		mc.gameSettings.keyBindForward.pressed = false;
	}
	

}
