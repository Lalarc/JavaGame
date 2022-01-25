package edu.uoc.uocnejitos.model;

public abstract class Piece extends Object{
	
	Coordinate coordinate;
	Symbol symbol;
	
	protected Piece (Coordinate coord, Symbol symbol) {
		setCoord(coord);
		setSymbol(symbol);
	}
	
	public boolean equals(Piece obj) {
		if (this.symbol == obj.symbol && 
				this.coordinate.column == obj.coordinate.column &&
				this.coordinate.row == obj.coordinate.row) {
			return true;
		}else {
			return false;
		}
	}
	
	public Coordinate getCoord() {
		return this.coordinate;
	}
	
	public Symbol getSymbol() {
		return this.symbol;
	}
	
	public void setCoord(int row, int column) {
		coordinate.row = row;
		coordinate.column = column;
	}
	
	public void setCoord(Coordinate coord) {
		this.coordinate = coord;
	}
	
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return ""+symbol.toString();
	}
}
