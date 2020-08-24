package stagger.main;

import java.util.ArrayList;

import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import stagger.gui.StaggerGuiManager;
import stagger.mods.*;
import stagger.utils.RenderXRay;

public class Stagger {
	
	private static ArrayList<Module> mods;
	
	public Stagger() {
		mods = new ArrayList<Module>();
		RenderXRay.initXRayBlocks();
		
		addMod(new FullBright()); //fill array with implemented hacks
		addMod(new MobDetection());
		addMod(new XRay()); //this is index 2. If moved, change code in Block.java at line 453 and 474
		
		addMod(new Flight());
		addMod(new NoFall());
		addMod(new WallClimb());
		addMod(new AutoMine());
		addMod(new AutoWalk());
		
		addMod(new BowAimbot());
		addMod(new FightBot());
		
		addMod(new ClickGui());
	}
	
	public static void addMod(Module m) {
		mods.add(m); // function to add mods
	}
	
	public static ArrayList<Module> getModules() {
		return mods;
	}
	
	public static void onUpdate() {
		for(Module m: mods) {
			m.onUpdate(); //Whenever a mod is activated call a method onUpdate within the mod IF IT HAS IT IMPLEMENTED. 
			//NOT THE SAME as Stagger's OnUpdate; this one is from Module.java
			//Triggers when an behavioral entity is changed (e.g. zombie dies)
		}
	}
	
	public static void onRender() {
		for(Module m: mods) {
			m.onRender(); //SLIGHTLY different than onUpdate; triggers when an enviromental entity is changed 
			//(e.g. a block is placed in the game, water flows, block destroyed etc)
		}
	}
	
	public static void onKeyPressed(int k) {
		for(Module m: mods) {
			if(m.getKey() == k) {
				m.toggle(); //Triggers when any key is pressed, cycles through mods again to see if 
				//any mod took it; if so, trigger the m.toggle() method and do whatever inside it
			}
		}
	}
	
}
