package stagger.mods;

import org.lwjgl.input.Keyboard;

import stagger.main.Category;

public class AutoMine extends Module{

	public AutoMine() {
		super("AutoMine", Keyboard.CHAR_NONE, Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			if(mc.gameSettings.keyBindAttack.pressed == false){
				mc.sendClickBlockToController(true);
			}
		}
	}

}
