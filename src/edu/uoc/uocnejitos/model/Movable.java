package edu.uoc.uocnejitos.model;

public interface Movable {

	default boolean isValidMove(Coordinate destination, Level level) {
		
		return true;
	}
	
	default boolean move(Coordinate destination, Level level) {
		
		return true;
	}
	
}
