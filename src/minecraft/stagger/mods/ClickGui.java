package stagger.mods;

import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import stagger.gui.StaggerGuiManager;
import stagger.main.Category;
import stagger.main.Stagger;

public class ClickGui extends Module{
	
	public static StaggerGuiManager guiManager;

	public ClickGui() {
		super("Click Gui", Keyboard.KEY_RSHIFT, Category.OTHER);
		guiManager = new StaggerGuiManager();
		guiManager.setTheme(new SimpleTheme());
		guiManager.setup();
	}
	
	@Override
	public void onEnable() {
		if(!(mc.currentScreen instanceof GuiManagerDisplayScreen)) {
			mc.displayGuiScreen(new GuiManagerDisplayScreen(guiManager));
			
			for(Frame f: guiManager.getFrames()) {
				f.update();
			}
			for(Frame f: guiManager.getFrames()) {
				if(f.isPinnable() || Minecraft.getMinecraft().currentScreen instanceof GuiManagerDisplayScreen) {
					f.render();
				}
			}
		}
		this.setToggled(false);
	}
	

}
