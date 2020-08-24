package stagger.mods;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import stagger.main.Category;
import stagger.utils.RenderMobBox;

public class MobDetection extends Module {

	public MobDetection() {
		super("MobDetection", Keyboard.CHAR_NONE, Category.ENVIRONMENT);
	}
	
	@Override
	public void onRender() {
		if(this.isToggled()) {
			for(Object o: mc.theWorld.loadedEntityList) {
				if(o instanceof EntityLiving) {
					RenderMobBox.mobBox((Entity)o, 0);
				}
			}
		}
	}

	
}
