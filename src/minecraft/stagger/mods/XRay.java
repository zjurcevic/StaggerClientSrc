package stagger.mods;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import stagger.main.Category;

public class XRay extends Module {
	
	public static ArrayList<Block> xrayBlocks = new ArrayList<Block>();
	
	public XRay() {
		super("XRay", Keyboard.KEY_X, Category.ENVIRONMENT);
	}

	
	@Override
	public void onEnable() {
		mc.renderGlobal.loadRenderers();
	}
	
	@Override
	public void onDisable() {
		mc.renderGlobal.loadRenderers();
	}
}
