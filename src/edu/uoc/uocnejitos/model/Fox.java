package edu.uoc.uocnejitos.model;

public class Fox extends Piece {

	
	FoxDirection direction;
	Fox foxOtherHalf = null;
	
	public Fox(Coordinate coord, Symbol symbol, FoxDirection direction) {
		super(coord, symbol);
		setDirection(direction);
	}
	
	public FoxDirection getDirection () {
		return direction;
	}
	
	public Fox getOtherHalf() {
		return foxOtherHalf;
	}
	
	private void setDirection(FoxDirection direction) {
		this.direction = direction;
	}
	
	protected void setOtherHalf(Fox otherHalf) {
		this.foxOtherHalf = otherHalf;
	}
}