package edu.uoc.uocnejitos.model;

public class FoxHead extends Fox{
	
	public FoxHead(Coordinate coord, FoxDirection direction) {

		super(coord,Symbol.FOX_HEAD_DOWN, direction);
		
		Symbol beginSymbol = Symbol.FOX_HEAD_DOWN;
		
		if (FoxDirection.DOWN == direction) {
			beginSymbol = Symbol.FOX_HEAD_DOWN;
		}
		
		if (FoxDirection.UP == direction) {
			beginSymbol = Symbol.FOX_HEAD_UP;
		}
		
		if (FoxDirection.RIGHT == direction) {
			beginSymbol = Symbol.FOX_HEAD_RIGHT;
		}
		
		if (FoxDirection.LEFT == direction) {
			beginSymbol = Symbol.FOX_HEAD_LEFT;
		}
		
		this.symbol = beginSymbol; 
		
		FoxTail tail = new FoxTail(this);
		this.setOtherHalf(tail);
		
	}
	private boolean calculateHorizontalCondition(int distance, int i, int end) {
		//TODO
		return false;
	}
	private boolean calculateVerticalCondition(int distance, int i, int end) {
		//TODO
		return false;	
	}
	
	public FoxTail getTail() {
		return (FoxTail)this.foxOtherHalf;
	}
	
	private boolean isValidHorizontalMove(Move move, Level level) {
		
		if (move.getEnd().column >= level.getSize() || move.getEnd().column < 0) {
			return false;
		}
		
		if(this.direction == FoxDirection.LEFT) {
			if(move.getColumnEnd() == 4) {
				return false;
			}
		}
		if(this.direction == FoxDirection.RIGHT) {
			if(move.getColumnEnd() == 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidMove(Coordinate destination, Level level) {
		
		Move move = new Move(coordinate,destination);
		
		if (isValidHorizontalMove(move, level) == true && isValidVerticalMove(move, level) == true) {
	
			return true;
		}else {
			return false;
		}
	}
	
	private boolean isValidVerticalMove(Move move, Level level) {
			
		if (move.getEnd().row >= level.getSize() || move.getEnd().row < 0) {
			return false;
		}
		if(this.direction == FoxDirection.UP) {
			if(move.getRowEnd() == 4) {
				return false;
			}
		}
		if(this.direction == FoxDirection.DOWN) {
			if(move.getRowEnd() == 0) {
				return false;
			}
		}
			return true;
	}
	
	public boolean move(Coordinate destination, Level level) throws LevelException {
		if (isValidMove(destination, level)) {

		
			//FOX HORIZONTAL 
			
			if(direction == FoxDirection.LEFT) {
				if(getCoord().column == destination.column) {
					return false;
				}
				
				int pointOne = 0;
				int pointTwo = 0;
				if(getCoord().column<destination.column) {
					pointOne = getCoord().column+2;
					pointTwo = destination.column+1;
				}
				if (getCoord().column>destination.column) {
					pointOne = destination.column;
					pointTwo = getCoord().column-1;
				}
				
				/*
					for(int i = pointOne; i <pointTwo; ++i) {
						if(true == level.isObstacle(new Coordinate(destination.row, i))) {
							return false;
						}
					}
				*/
				
				Grass grass = new Grass(this.coordinate);
				level.setFoxPiece(this.coordinate, grass);
							
				Grass grassTail = new Grass(getOtherHalf().getCoord());
				level.setFoxPiece(getOtherHalf().getCoord(), grassTail);
				
				
				getOtherHalf().setCoord(destination.row, destination.column+1);
				level.setFoxPiece(getOtherHalf().getCoord(), getOtherHalf());
			}
			
			if(direction == FoxDirection.RIGHT) {
				if(getCoord().column == destination.column) {
					return false;
				}
		
				int pointOne = getCoord().column;
				int pointTwo = destination.column;
				if (getCoord().column > destination.column) {
					pointOne = destination.column+1;
					pointTwo = getCoord().column+1;
				}
				
				
				for(int i = pointOne; i <pointTwo; ++i) {
					if(true == level.isObstacle(new Coordinate(destination.row, i))) {
						return false;
					}
				}
		
				
				Grass grass = new Grass(this.coordinate);
				level.setFoxPiece(this.coordinate, grass);
			
				Grass grassTail = new Grass(getOtherHalf().getCoord());
				level.setFoxPiece(getOtherHalf().getCoord(), grassTail);
				
				getOtherHalf().setCoord(destination.row, destination.column-1);
				level.setFoxPiece(getOtherHalf().getCoord(), getOtherHalf());
				
			}
			
			//FOX VERTICAL
			
			if(direction == FoxDirection.UP) {
				if(getCoord().row == destination.row) {
					return false;
				}
			
				int pointOne = getCoord().row+1;
				int pointTwo = destination.row+1;
				if (getCoord().row > destination.row) {
					pointOne = destination.row;
					pointTwo = getCoord().row;
				}
				
				for(int i = pointOne; i <pointTwo; ++i) {
					
					if(true == level.isObstacle(new Coordinate(i, destination.column))) {
						return false;
					}
				}
				
				
				Grass grass = new Grass(this.coordinate);
				level.setFoxPiece(this.coordinate, grass);
				
				Grass grassTail = new Grass(getOtherHalf().getCoord());
				level.setFoxPiece(getOtherHalf().getCoord(), grassTail);
				
				getOtherHalf().setCoord(destination.row+1, destination.column);
				level.setFoxPiece(getOtherHalf().getCoord(), getOtherHalf());
			}
			
			if(direction == FoxDirection.DOWN) {
				if(getCoord().row == destination.row) {
					return false;
				}
				
				int pointOne = getCoord().row;
				int pointTwo = destination.row;
				if (getCoord().row > destination.row) {
					pointOne = destination.row+1;
					pointTwo = getCoord().row+1;
				}
				
				
				for(int i = pointOne; i <pointTwo; ++i) {
					if(true == level.isObstacle(new Coordinate(i, destination.column))) {
						return false;
					}
				}
				
				
				Grass grass = new Grass(this.coordinate);
				level.setFoxPiece(this.coordinate, grass);
		
				Grass grassTail = new Grass(getOtherHalf().getCoord());
				level.setFoxPiece(getOtherHalf().getCoord(), grassTail);
				
				getOtherHalf().setCoord(destination.row-1, destination.column);
				level.setFoxPiece(getOtherHalf().getCoord(), getOtherHalf());
				
			}
	
			
			setCoord(destination);
			level.setFoxPiece(destination, this);
			
			return true;
		}
		return false;
	}

}
