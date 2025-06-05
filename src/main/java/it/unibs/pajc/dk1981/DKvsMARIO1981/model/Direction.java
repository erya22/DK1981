package it.unibs.pajc.dk1981.DKvsMARIO1981.model;

public enum Direction {
	RIGHT(false, true),
	LEFT(true, false),
	JUMPL(true, false),
	JUMPR(false, true),
	CLIMB(false, true);
	
	boolean left = false;
	boolean right = false;
	Direction(boolean left, boolean right) {
		this.left = left;
		this.right = right;
	}
	public boolean isLeft() {
		return left;
	}
	public boolean isRight() {
		return right;
	}
	
	

}
