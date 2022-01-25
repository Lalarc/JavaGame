package edu.uoc.uocnejitos.model;

public class Bunny extends Piece {
	
	public Bunny(Coordinate coord) {
		
		super(coord, Symbol.BUNNY_BROWN);
	}
	
	public Bunny(Coordinate coord, Symbol symbol) {
		
		super(coord, symbol);
	}
	
	public boolean isInHole() {
		if( this.symbol == Symbol.BUNNY_BROWN_HOLE ||
			this.symbol == Symbol.BUNNY_GRAY_HOLE  ||
			this.symbol == Symbol.BUNNY_WHITE_HOLE
		) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean isValidHorizontalMove(Move move, Level level) {
		
		if (move.getEnd().column > level.getSize() || move.getEnd().column < 0) {
			return false;
		}
		
		if (level.isObstacle(move.getEnd())) {
			return false;
		}
		
		if (move.getHorizontalDistance() == 1) {
			return false;
		}
		
		int pointOne = move.coordStart.column;
		int pointTwo = move.coordEnd.column;
		if (move.coordStart.column > move.coordEnd.column) {
			pointOne = move.coordEnd.column;
			pointTwo = move.coordStart.column;
		}
		
		for(int i = pointOne + 1; i <pointTwo; ++i) {
			if(false == level.isObstacle(new Coordinate(move.getRowEnd(), i))) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isValidMove(Coordinate destination, Level level) {
		
		Move move = new Move(coordinate,destination);
		
		if (move.getHorizontalDistance() != 0 && move.getVerticalDistance() != 0) {
			return false;
		}
		
		if (isValidHorizontalMove(move, level) && isValidVerticalMove(move, level)) {
			return true;
		}
		
		return false;
	}
	
	private boolean isValidVerticalMove(Move move, Level level) {

		if (move.getEnd().row > level.getSize() || move.getEnd().row < 0) {
			return false;
		}
		
		if (level.isObstacle(move.getEnd())) {
			return false;
		}
		
		if (move.getVerticalDistance() == 1) {
			return false;
		}
		
		int pointOne = move.coordStart.row;
		int pointTwo = move.coordEnd.row;
		if (move.coordStart.row > move.coordEnd.row) {
			pointOne = move.coordEnd.row;
			pointTwo = move.coordStart.row;
		}
		
		for(int i = pointOne + 1; i <pointTwo; ++i) {
			if(false == level.isObstacle(new Coordinate(i, move.getColumnEnd()))) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean move(Coordinate coord, Level level) throws LevelException {
		
		if (null == coord || null == level) {
			return false;
		}
		
		
		if (isValidMove(coord, level)) {
			
			Grass grass = new Grass(coordinate);
			level.setPiece(coordinate, grass);
			
			Piece destinationPiece = level.getPiece(coord);
		
			
			if (destinationPiece.symbol == Symbol.HOLE) {
				if (symbol == Symbol.BUNNY_BROWN) {
					symbol = Symbol.BUNNY_BROWN_HOLE;
				}
				
				if (symbol == Symbol.BUNNY_WHITE) {
					symbol = Symbol.BUNNY_WHITE_HOLE;
				}
				
				if (symbol == Symbol.BUNNY_GRAY) {
					symbol = Symbol.BUNNY_GRAY_HOLE;
				}
			}
			
			if (destinationPiece.symbol == Symbol.GRASS) {
				if (symbol == Symbol.BUNNY_BROWN_HOLE) {
					symbol = Symbol.BUNNY_BROWN;
				}
				
				if (symbol == Symbol.BUNNY_WHITE_HOLE) {
					symbol = Symbol.BUNNY_WHITE;
				}
				
				if (symbol == Symbol.BUNNY_GRAY_HOLE) {
					symbol = Symbol.BUNNY_GRAY;
				}
			}
			
			coordinate = coord;
			return true;
		}
		return false;
	}
	
}
