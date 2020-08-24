package stagger.mods;

import net.minecraft.client.Minecraft;
import stagger.main.Category;

public class Module {
	
	protected Minecraft mc = Minecraft.getMinecraft();
	private String name; //name of the hack
	private int key; // key for keyboard
	private boolean toggled; // is it on or off (hack)
	private Category category;
	
	private long currentMS = 0L;
	protected long lastMS = -1L;
	
	public Module(String nm, int k, Category c){
		name = nm;
		key = k;
		category = c;
		toggled = false;
	}
	
	public void toggle() {
		toggled = !toggled;
		if (toggled) {
			onEnable(); //shortcut to enable/disable mods
		}else {
			onDisable();
		}
	}
	
	public void onEnable(){
		//intentionally empty because this gets overriden by each individual mod
	}
	public void onDisable(){
		//each hack has its own personalized logic
	}
	public void onUpdate(){
		
	}
	public void onRender(){
		
	}

	

	public Category getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}
	
	
	public void updateMS() {
		this.currentMS = System.currentTimeMillis();
	}
	public void updateLastMS() {
		this.lastMS = System.currentTimeMillis();
	}
	public boolean hasTimePassed(float speed) {
		if(this.currentMS >= this.lastMS + (long)(1000 / speed)) {
			return true;
		} else {
			return false;
		}
	}
	
}
