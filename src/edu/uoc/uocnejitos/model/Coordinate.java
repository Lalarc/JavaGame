package edu.uoc.uocnejitos.model;

public class Coordinate implements Comparable<Coordinate> {
	
	int row, column;
	
	Piece piece;
	
	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int compareTo(Coordinate coordinate) {
		
		if (this.row == coordinate.row) {
			if(this.column == coordinate.column) {
				return 0;
			}else if(this.column < coordinate.column) {
				return -1;
			}else {
				return 1;
			}
		} else if(this.row < coordinate.row) {
			return -1;
		}else {
			return 1;
		}	
	}
	
	public boolean equals(Coordinate obj) {
		
		if (row == obj.row && column == obj.column) {
			return true;
		}else {
			return false;
		}
	}
	
	public int getColumn() {
		
		return this.column;
	}
	
	public int getRow() {
		
		return this.row;
	}
	
	public void setColumn(int column) {
		
		this.column = column;	
	}
	
	public void setRow(int row) {
		
		this.row = row;
	}
	
	@Override
	public String toString() {
		//TODO
		return "("+row+","+column+")";
	}
}
