package edu.uoc.uocnejitos.model;

public class LevelException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public final static String ERROR_SIZE = "[ERROR] Board's size must be greater than 2!!";
	public final static String ERROR_MIN_MOVES = "[ERROR] Minimum number of moves must be at least 1!!";
	public final static String ERROR_NO_BUNNIES = "[ERROR] This level does not hace any bunny!!";
	public final static String ERROR_NO_HOLES = "[ERROR] This level does not have any hole";
	public final static String ERROR_MORE_BUNNIES_THAN_HOLES = "[ERROR] This level has more bunnies than holes!!";
	public final static String ERROR_INCORRECT_ROW = "[ERROR] This row does not exist!!";
	public final static String ERROR_INCORRECT_COLUMN = "[ERROR] This column does not exist!!";
	public final static String ERROR_COORDINATE = "[ERROR] This coordinate is incorrect!!";
	
	public LevelException() {
		super();
	}
	
	public LevelException(String msg) {
		super(msg);
	}
	
}
