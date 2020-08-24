package stagger.utils;

import net.minecraft.block.Block;
import stagger.mods.XRay;

public class RenderXRay {
	
	public static void initXRayBlocks() {
		XRay.xrayBlocks.add(Block.getBlockFromName("coal_ore"));
		XRay.xrayBlocks.add(Block.getBlockFromName("iron_ore"));
		XRay.xrayBlocks.add(Block.getBlockFromName("gold_ore"));
		XRay.xrayBlocks.add(Block.getBlockFromName("redstone_ore"));
		XRay.xrayBlocks.add(Block.getBlockById(74));//Redstone ore glowing
		XRay.xrayBlocks.add(Block.getBlockFromName("lapis_ore"));
		XRay.xrayBlocks.add(Block.getBlockFromName("diamond_ore"));
		XRay.xrayBlocks.add(Block.getBlockFromName("emerald_ore"));
		XRay.xrayBlocks.add(Block.getBlockFromName("quartz_ore"));
		XRay.xrayBlocks.add(Block.getBlockFromName("clay"));
		XRay.xrayBlocks.add(Block.getBlockFromName("glowstone"));
		XRay.xrayBlocks.add(Block.getBlockById(8));//Water
		XRay.xrayBlocks.add(Block.getBlockById(9));//Water flowing
		XRay.xrayBlocks.add(Block.getBlockById(10));//Lava
		XRay.xrayBlocks.add(Block.getBlockById(11));//Lava flowing
		XRay.xrayBlocks.add(Block.getBlockFromName("crafting_table"));
		XRay.xrayBlocks.add(Block.getBlockById(61));//Furnace
		XRay.xrayBlocks.add(Block.getBlockById(62));//Furnace on
		XRay.xrayBlocks.add(Block.getBlockFromName("torch"));
		XRay.xrayBlocks.add(Block.getBlockFromName("ladder"));
		XRay.xrayBlocks.add(Block.getBlockFromName("tnt"));
		XRay.xrayBlocks.add(Block.getBlockFromName("coal_block"));
		XRay.xrayBlocks.add(Block.getBlockFromName("iron_block"));
		XRay.xrayBlocks.add(Block.getBlockFromName("gold_block"));
		XRay.xrayBlocks.add(Block.getBlockFromName("diamond_block"));
		XRay.xrayBlocks.add(Block.getBlockFromName("emerald_block"));
		XRay.xrayBlocks.add(Block.getBlockFromName("redstone_block"));
		XRay.xrayBlocks.add(Block.getBlockFromName("lapis_block"));
		XRay.xrayBlocks.add(Block.getBlockFromName("fire"));
		XRay.xrayBlocks.add(Block.getBlockFromName("mossy_cobblestone"));
		XRay.xrayBlocks.add(Block.getBlockFromName("mob_spawner"));
		XRay.xrayBlocks.add(Block.getBlockFromName("end_portal_frame"));
		XRay.xrayBlocks.add(Block.getBlockFromName("enchanting_table"));
		XRay.xrayBlocks.add(Block.getBlockFromName("bookshelf"));
		XRay.xrayBlocks.add(Block.getBlockFromName("command_block"));
	}
	
	public static boolean isXrayBlock(Block b) {
		if(XRay.xrayBlocks.contains(b)) {
			return true;
		}
		return false;
	}
	
}
