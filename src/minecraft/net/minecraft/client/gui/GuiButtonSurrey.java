package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonSurrey extends GuiButton {
	
	private static final ResourceLocation SURREY_ICON = new ResourceLocation("textures/gui/title/stagger_surrey.png");
    private static final ResourceLocation SURREY_HOVER_ICON = new ResourceLocation("textures/gui/title/stagger_surrey2.png");
	private int x;
	private int y;
	private int widthIn;
	private int heightIn;
	
    public GuiButtonSurrey(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
    	super(buttonId, x, y, widthIn, heightIn, "");
    	this.x = x;
    	this.y = y;
    	this.widthIn = widthIn;
    	this.heightIn = heightIn;
    }
    
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if(this.visible)
        {
            boolean mouseHover = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            if(mouseHover)
            {
                mc.getTextureManager().bindTexture(SURREY_HOVER_ICON);
            }
            else
            {
                mc.getTextureManager().bindTexture(SURREY_ICON);
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            Gui.drawScaledCustomSizeModalRect(this.xPosition, this.yPosition, 0, 0, 368, 368, this.widthIn, this.heightIn, 368, 368);
        }
    }
    
}
