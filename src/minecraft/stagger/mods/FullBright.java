package stagger.mods;

import org.lwjgl.input.Keyboard;

import stagger.main.Category;

public class FullBright extends Module{

	public FullBright() {
		super("FullBright", Keyboard.KEY_P, Category.ENVIRONMENT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.gameSettings.gammaSetting = 100F;
		} else {
			mc.gameSettings.gammaSetting = 0f;
		}
	}

}
