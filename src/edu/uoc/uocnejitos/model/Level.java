package edu.uoc.uocnejitos.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * Level class.
 * @author David GarcÃ­a SolÃ³rzano
 * @version 1.0  
 */
public class Level {
	/**
	 * Size of the board. SIZExSIZE.
	 */
	private final int SIZE;
	/**
	 * Level's difficulty
	 */
	private final LevelDifficulty DIFFICULTY;
	/**
	 * Number minimum of moves that are required in order to beat the level/challenge.
	 */
	private final int MIN_MOVES;
	/**
	 * List of pieces that are on the board.	
	 */
	private List<Piece> board;
	
	/**
	 * Constructor
	 * @param fileName Name of the configuration file which has all the information of the level.
	 * @throws FileNotFoundException When it is impossible to open the configuration file.
	 * @throws IllegalArgumentException When an error of this type happens, e.g. NumberFormatException.
	 * @throws LevelException When there is an error with the information of configuration file, e.g. incorrect size or min moves, no bunnies, no holes, #bunnies&lt;#holes
	 */
	public Level(String fileName) throws FileNotFoundException, IllegalArgumentException, LevelException {
		String line = null;
		long numBunnies = 0, numHoles = 0;
		int row = 0, column = 0;
		char pieceSymbol = ' ';
		Piece piece = null;
		
		
		try(Scanner sc = new Scanner(new File(fileName))){
			SIZE = Integer.parseInt(sc.nextLine());
		
			if(getSize()<3)
				throw new LevelException(LevelException.ERROR_SIZE);
			
			DIFFICULTY = LevelDifficulty.valueOf(sc.nextLine().toUpperCase());
			
			MIN_MOVES = Integer.parseInt(sc.nextLine());
			
			if(getMinMoves()<1)
				throw new LevelException(LevelException.ERROR_MIN_MOVES);
			
			board = new ArrayList<Piece>(SIZE*SIZE);
			
			//We populate the whole list with Grass pieces.			
			for(int i = 0; i < getSize(); i++) {
				for(int j = 0; j < getSize(); j++) {
					board.add(new Grass(new Coordinate(i, j)));
				}
			}			
			
			
			while(sc.hasNext()) {
				line = sc.nextLine();
				pieceSymbol= line.charAt(0);
				
				if(pieceSymbol != 'b' && pieceSymbol != 'B'
						&&
						pieceSymbol != 'w' && pieceSymbol != 'W'
						&&
						pieceSymbol != 'g' && pieceSymbol != 'g') { 
					pieceSymbol = Character.toUpperCase(pieceSymbol); 
				}
					
				row = calculateRow(line.toLowerCase().charAt(1)); 
				column = calculateColumn(line.toLowerCase().charAt(2));
				
				switch(Symbol.getName(pieceSymbol)) {
					case HOLE:
						piece = new Hole(new Coordinate(row,column));
						break;
					case MUSHROOM:
						piece = new Mushroom(new Coordinate(row,column));
						break;
					case BUNNY_WHITE:						
					case BUNNY_WHITE_HOLE:
					case BUNNY_BROWN:
					case BUNNY_BROWN_HOLE:
					case BUNNY_GRAY:
					case BUNNY_GRAY_HOLE:
						piece = new Bunny(new Coordinate(row,column),Symbol.getName(pieceSymbol));
						break;
					case FOX_HEAD_UP:
					case FOX_HEAD_DOWN:
					case FOX_HEAD_LEFT:
					case FOX_HEAD_RIGHT:						
						String direction = Symbol.getName(pieceSymbol).getImageSrc().split("-")[2];
						direction = direction.substring(0,direction.indexOf(".")).toUpperCase();
						FoxHead fox = new FoxHead(new Coordinate(row,column),FoxDirection.valueOf(direction));
						piece = fox;
						FoxTail tail = fox.getTail();						
						board.set((tail.getCoord().getRow()*getSize())+tail.getCoord().getColumn(),tail);
						break;
				default:
					break;					
				}			
				board.set((row*getSize())+column,piece);
			}
						
			numBunnies = getBoard1D().stream().filter(p -> p instanceof Bunny).count();
			numHoles = getBoard1D().stream().filter(p -> p instanceof Hole || p.getSymbol().getImageSrc().contains("-hole")).count();
			
			if(numBunnies==0)		
			 throw new LevelException(LevelException.ERROR_NO_BUNNIES);
			
			if(numHoles==0)
				throw new LevelException(LevelException.ERROR_NO_HOLES);
			
			if(numHoles<numBunnies) throw new LevelException(LevelException.ERROR_MORE_BUNNIES_THAN_HOLES);
			             
		}catch(FileNotFoundException e) {
			throw e;
		}
		
	}

	private int calculateColumn(char ColumnChar) throws LevelException {
		
		int value = ColumnChar - 49;
		
		if (value < 0 || value > SIZE-1)
			throw new LevelException(LevelException.ERROR_INCORRECT_COLUMN);
		else {
			return value;
		}
	}
	
	private int calculateRow(char letter) throws LevelException {
		
		int value = letter - 97;
		
		if (value < 0 || value > SIZE-1)
			throw new LevelException(LevelException.ERROR_INCORRECT_COLUMN);
		else {
			return value;
		}
	}
	
	public List<Piece> getBoard1D() {
		return board;
	}
	
	public Piece[][] getBoard2D() {
		
		Piece pieces[][] = new Piece[SIZE][SIZE];
		
		for (Piece element : board) {
			int column = element.coordinate.column;
			int row = element.coordinate.row;
			pieces[row][column] = element;
		}	
		return pieces;
	}
	
	public LevelDifficulty getDifficulty() {
	
		return DIFFICULTY;
	}
	
	public int getMinMoves() {
	
		return MIN_MOVES;
	}
	
	public Piece getPiece(int row, int column) throws LevelException {
		
		if (row<SIZE&&row>=0&&column<SIZE&&column>=0) {
			Piece pieces[][] = new Piece[SIZE][SIZE];
			
			for (Piece element : board) {
				int pieceColumn = element.coordinate.column;
				int pieceRow = element.coordinate.row;
				pieces[pieceRow][pieceColumn] = element;
			}	
			return pieces[row][column];
			
		}else {
		
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}
	}
	
	public Piece getPiece(Coordinate coord) throws LevelException {
	
		return getPiece(coord.row,coord.column);
	}
	
	public int getSize() {
	
		return SIZE;
	}
	
	public boolean isFinished() {
		
		Piece pieces[][] = new Piece[SIZE][SIZE];
		for (Piece element : board) {
			int pieceColumn = element.coordinate.column;
			int pieceRow = element.coordinate.row;
			pieces[pieceRow][pieceColumn] = element;
		}	

		int checkBunnys = 0;
		for (int i = 0; i<SIZE; i++) {
			for (int j = 0; j<SIZE; j++) {
				if(pieces[i][j].symbol == Symbol.BUNNY_WHITE || pieces[i][j].symbol == Symbol.BUNNY_BROWN || pieces[i][j].symbol == Symbol.BUNNY_GRAY) {
					checkBunnys++;
				}
			}
		}
		
		if (checkBunnys != 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isObstacle(int row, int column) {
	
		Piece piece = getBoard2D()[row][column];
		
		if (piece.symbol == Symbol.GRASS || piece.symbol == Symbol.HOLE) {
			return false;
		}	
		return true;
	}
	
	public boolean isObstacle(Coordinate coord) {
		Piece piece = getBoard2D()[coord.row][coord.column];
		
		if (piece.symbol == Symbol.GRASS || piece.symbol == Symbol.HOLE) {
			return false;
		}
		return true;
	}
	
	public void setPiece(Coordinate coord,Piece piece) throws LevelException {
		//TODO
		if (coord.column<0||coord.column>=SIZE||coord.row<0||coord.row>=SIZE) {
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}else {
			int checkPosition = 0;
			for (int j = 0; j < coord.column; j++) {
				for (int i = 0; i < coord.row; i++) {
					checkPosition++;
				}
			}
			board.set(checkPosition, piece);
		}
	}
	
	public void setFoxPiece(Coordinate coord, Piece piece) {
			int checkPosition = 0;
			for (int j = 0; j < coord.row; j++) {
				checkPosition += 5;
			}
			for (int i = 0; i < coord.column; i++) {
					checkPosition++;
			}
			
			board.set(checkPosition, piece);
	}
	
	@Override
	public String toString() {
		//TODO
		Piece pieces[][] = new Piece[SIZE][SIZE];
		for (Piece element : board) {
			int pieceColumn = element.coordinate.column;
			int pieceRow = element.coordinate.row;
			pieces[pieceRow][pieceColumn] = element;
		}
		if(SIZE==3) {
			return  "  123"
					+"\na|"+pieces[0][0].symbol+pieces[0][1].symbol+pieces[0][2].symbol
					+"\nb|"+pieces[1][0].symbol+pieces[1][1].symbol+pieces[1][2].symbol
					+"\nc|"+pieces[2][0].symbol+pieces[2][1].symbol+pieces[2][2].symbol
					+"\n"
					;
		}
		if(SIZE==4) {
			return  "  1234"
					+"\na|"+pieces[0][0].symbol+pieces[0][1].symbol+pieces[0][2].symbol+pieces[0][3].symbol
					+"\nb|"+pieces[1][0].symbol+pieces[1][1].symbol+pieces[1][2].symbol+pieces[1][3].symbol
					+"\nc|"+pieces[2][0].symbol+pieces[2][1].symbol+pieces[2][2].symbol+pieces[2][3].symbol
					+"\nd|"+pieces[3][0].symbol+pieces[3][1].symbol+pieces[3][2].symbol+pieces[3][3].symbol
					+"\n"
					;
		}
		return  "  12345"
				+"\na|"+pieces[0][0].symbol+pieces[0][1].symbol+pieces[0][2].symbol+pieces[0][3].symbol+pieces[0][4].symbol
				+"\nb|"+pieces[1][0].symbol+pieces[1][1].symbol+pieces[1][2].symbol+pieces[1][3].symbol+pieces[1][4].symbol
				+"\nc|"+pieces[2][0].symbol+pieces[2][1].symbol+pieces[2][2].symbol+pieces[2][3].symbol+pieces[2][4].symbol
				+"\nd|"+pieces[3][0].symbol+pieces[3][1].symbol+pieces[3][2].symbol+pieces[3][3].symbol+pieces[3][4].symbol
				+"\ne|"+pieces[4][0].symbol+pieces[4][1].symbol+pieces[4][2].symbol+pieces[4][3].symbol+pieces[4][4].symbol
				+"\n"
				;
	}
	private boolean validatePosition(int row, int column) {

		if (column<0||column>=SIZE||row<0||row>=SIZE) {
			return false;
		}else {
			return true;
		}
	}
}