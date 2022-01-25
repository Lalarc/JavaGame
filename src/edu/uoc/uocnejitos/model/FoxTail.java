package edu.uoc.uocnejitos.model;

public class FoxTail extends Fox{
	
		
	public FoxTail(FoxHead foxHead) {
		super(foxHead.coordinate, Symbol.FOX_TAIL_DOWN, FoxDirection.DOWN);
		
		if (null == this.foxOtherHalf) {
			this.foxOtherHalf = foxHead;
			this.direction = foxHead.direction;
		} 
		
		this.coordinate = calculateCoord(this.foxOtherHalf.coordinate);
		
	
		
		if (FoxDirection.DOWN == foxHead.direction) {
			symbol = Symbol.FOX_TAIL_DOWN;
		}
		
		if (FoxDirection.UP == foxHead.direction) {
			symbol = Symbol.FOX_TAIL_UP;
		}
		
		if (FoxDirection.RIGHT == foxHead.direction) {
			symbol = Symbol.FOX_TAIL_RIGHT;
		}
		
		if (FoxDirection.LEFT == foxHead.direction) {
			symbol = Symbol.FOX_TAIL_LEFT;
		}
		this.setOtherHalf(foxHead);
		foxHead.setOtherHalf(this);
	}
	
	public Coordinate calculateCoord(Coordinate coordHead) {
		
		Coordinate foxTailCoord = new Coordinate(0,0);
		
		
		if(getOtherHalf().direction == FoxDirection.DOWN) {
			foxTailCoord.column = coordHead.column;
			foxTailCoord.row = coordHead.row-1;
		}
		if(getOtherHalf().direction == FoxDirection.UP) {
			foxTailCoord.column = coordHead.column;
			foxTailCoord.row = coordHead.row+1;
		}
		if(getOtherHalf().direction == FoxDirection.RIGHT) {
			foxTailCoord.column = coordHead.column-1;
			foxTailCoord.row = coordHead.row;
		}
		if(getOtherHalf().direction == FoxDirection.LEFT) {
			foxTailCoord.column = coordHead.column+1;
			foxTailCoord.row = coordHead.row;
		}
		return foxTailCoord;
	}
	
	private Coordinate getHeadEndCoordinate(Coordinate tailDestination, int sizeBoard) {
		
		Coordinate foxHeadCoord = new Coordinate(0,0);
		
		if(direction == FoxDirection.DOWN) {
			foxHeadCoord.column = tailDestination.column;
			foxHeadCoord.row = tailDestination.row+1;
		}
		if(direction == FoxDirection.UP) {
			foxHeadCoord.column = tailDestination.column;
			foxHeadCoord.row = tailDestination.row-1;
		}
		if(direction == FoxDirection.RIGHT) {
			foxHeadCoord.column = tailDestination.column+1;
			foxHeadCoord.row = tailDestination.row;
		}
		if(direction == FoxDirection.LEFT) {
			foxHeadCoord.column = tailDestination.column-1;
			foxHeadCoord.row = tailDestination.row;
		}
		if(foxHeadCoord.column<0||foxHeadCoord.row<0||foxHeadCoord.column>=sizeBoard||foxHeadCoord.row>=sizeBoard) {
			return null;
		}else {
			return foxHeadCoord;
		}
	}
	
	public boolean isValidMove(Coordinate destination, Level level) {
	
		if (destination.column >= level.getSize() || destination.column < 0) {
			return false;
		}
		if (destination.column == coordinate.column && destination.row == coordinate.row) {
			return false;
		}
		if (destination.row >= level.getSize() || destination.row < 0) {
			return false;
		}
		if(this.direction == FoxDirection.LEFT) {
			if(destination.column == 0) {
				return false;
			}
		}
		if(this.direction == FoxDirection.RIGHT) {
			if(destination.column == 4) {
				return false;
			}	
		}
		if(this.direction == FoxDirection.UP) {
			if(destination.row == 0) {
				return false;
			}
		}
		if(this.direction == FoxDirection.DOWN) {
			if(destination.row == 4) {
				return false;
			}
		}
		return true;
		
	}
	
	public boolean move(Coordinate destination, Level level) {
	
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
				
				
				getOtherHalf().setCoord(destination.row, destination.column-1);
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
				/*
				//if(destination != getOtherHalf().coordinate) {
				for(int i = pointOne; i <pointTwo; ++i) {
					if(true == level.isObstacle(new Coordinate(destination.row, i))) {
						return false;
					}
				}*/
				
				
				Grass grass = new Grass(this.coordinate);
				level.setFoxPiece(this.coordinate, grass);
			
				Grass grassTail = new Grass(getOtherHalf().getCoord());
				level.setFoxPiece(getOtherHalf().getCoord(), grassTail);
				
				getOtherHalf().setCoord(destination.row, destination.column+1);
				level.setFoxPiece(getOtherHalf().getCoord(), getOtherHalf());
				
			}
			
			//FOX VERTICAL
			
			if(direction == FoxDirection.UP) {
				if(getCoord().row == destination.row) {
					return false;
				}
				int pointOne = 0;
				int pointTwo = 0;
				
				if (getCoord().row < destination.row) {		
					pointOne = getCoord().row+2;
				 	pointTwo = destination.row+1;
				}
				
				 if (getCoord().row > destination.row) { 
					 pointOne = destination.row-1;
					 pointTwo = getCoord().row-2;
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
			
			if(direction == FoxDirection.DOWN) {
				if(getCoord().row == destination.row) {
					return false;
			}
				
			int pointOne = getCoord().row-2;
			int pointTwo = destination.row-1;
			if (getCoord().row > destination.row) {
				pointOne = destination.row+1;
				pointTwo = getCoord().row+2;
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
	
			
			setCoord(destination);
			level.setFoxPiece(destination, this);
			
			return true;
		}
		return false;
	}
}
