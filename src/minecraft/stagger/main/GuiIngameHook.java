package stagger.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import stagger.mods.Module;

public class GuiIngameHook extends GuiIngame{

	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
	}
	
	@Override
	public void renderGameOverlay(float partialTicks){
		super.renderGameOverlay(partialTicks);
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        this.mc.entityRenderer.setupOverlayRendering();
        GlStateManager.enableBlend();
        
        int count = 0;
        for(Module m: Stagger.getModules()) {
        	if(m.isToggled()) {
        		count++;
        		mc.fontRendererObj.drawString("Active modules:", 2, 2, 0xffffff, true);
        		mc.fontRendererObj.drawString(m.getName(), 2, 2+(count*10), getCoror(m.getCategory()), true);
        	}
        }
        
    }

	private int getCoror(Category category) {
		if (category == Category.PLAYER) {
			return 0x8fe0ff;
		}
		else if (category == Category.ENVIRONMENT) {
			return 0x66ff66;
		}
		else if (category == Category.COMBAT) {
			return 0xff7373;
		}
		else {
			return 0xffffff;
		}
	}
	
	
}
