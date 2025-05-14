package main;

import object.Hammer;

public class AssetSetter {
	
	public static final int XHAMMER = 26;
	public static final int YHAMMER = 22;
	public static final int XHAMMER1 = 23;
	public static final int YHAMMER1 = 9;
	
	GamePnl gp;
	
	public AssetSetter(GamePnl gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new Hammer();
		gp.obj[0].worldX = XHAMMER * gp.tileSize;
		gp.obj[0].worldY = YHAMMER * gp.tileSize;
		
		gp.obj[1] = new Hammer();
		gp.obj[1].worldX = XHAMMER1 * gp.tileSize;
		gp.obj[1].worldY = YHAMMER1 * gp.tileSize;
	}

}
