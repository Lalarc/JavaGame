package edu.uoc.uocnejitos.model;

public class Move {
	
	Coordinate coordStart = new Coordinate(0,0);
	Coordinate coordEnd = new Coordinate(0,0);
	
	public Move (int xStart, int yStart, int xEnd, int yEnd) {	

		this.coordStart.column = yStart;
		this.coordStart.row = xStart;
		this.coordEnd.column = yEnd;
		this.coordEnd.row = xEnd;
	}
	
	public Move (Coordinate coordStart, Coordinate coordEnd) {

		this.coordStart = coordStart;
		this.coordEnd = coordEnd;
	}
	
	public int getColumnEnd() {
	
		return coordEnd.column;
	}
	
	public int getColumnStart() {
		
		return coordStart.column;
	}
	
	public Coordinate getStart() {
		
		return coordStart;
	}
	
	public MoveDirection getDirection() {
		
		if (coordStart.column == coordEnd.column && coordStart.row != coordEnd.row) {	
			return MoveDirection.VERTICAL;
		}else if (coordStart.column != coordEnd.column && coordStart.row == coordEnd.row) {
			return MoveDirection.HORIZONTAL;
		}else {
			return MoveDirection.INVALID;
		}
	}
	
	public Coordinate getEnd() {
		
			return coordEnd;
	}
	
	public int getHorizontalDistance() {	
		
			return coordEnd.column-coordStart.column;
	}
	
	public int getRowEnd() {
	
		return coordEnd.row;
	}
	
	public int getRowStart() {

		return coordStart.row;
	}
	
	public int getVerticalDistance() {

		return coordEnd.row-coordStart.row;
	}
	
	public void setColumnEnd(int ColumnEnd) {
	
		this.coordEnd.column = ColumnEnd;
	}
	
	public void setColumnStart(int ColumnStart) {
	
		this.coordStart.column = ColumnStart;
	}
	
	public void setEnd(Coordinate end) {
	
		this.coordEnd = end;
	}
	
	public void setRowEnd(int RowEnd) {
	
		this.coordEnd.row = RowEnd;
	}
	
	public void setRowStart(int RowStart) {
		
		this.coordStart.row = RowStart;
	}
	
	public void setStart(Coordinate start) {
	
		this.coordStart = start;
	}
	
	@Override
	public String toString() {
		return "("+getRowStart()+","+getColumnStart()+") --> ("+getRowEnd()+","+getColumnEnd()+") : "+getDirection();
	}
}
