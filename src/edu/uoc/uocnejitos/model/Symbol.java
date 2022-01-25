package edu.uoc.uocnejitos.model;

public enum Symbol {
	
	BUNNY_WHITE('w',"bunny-white.png"),
	BUNNY_WHITE_HOLE('W',"bunny-white-hole.png"),
	BUNNY_GRAY('g',"bunny-gray.png"),
	BUNNY_GRAY_HOLE('G',"bunny-gray-hole.png"),
	BUNNY_BROWN('b',"bunny-brown.png"),
	BUNNY_BROWN_HOLE('B',"bunny-brown-hole.png"),
	FOX_HEAD_UP('^',"fox-head-up.png"),
	FOX_TAIL_UP('⊥',"fox-tail-up.png"),
	FOX_HEAD_RIGHT('>',"fox-head-right.png"),
	FOX_TAIL_RIGHT('⊢',"fox-tail-right.png"),
	FOX_HEAD_DOWN('V',"fox-head-down.png"),
	FOX_TAIL_DOWN('T',"fox-tail-down.png"),
	FOX_HEAD_LEFT('<',"fox-head-left.png"),
	FOX_TAIL_LEFT('⊣',"fox-tail-left.png"),
	MUSHROOM('M',"mushroom.png"),
	HOLE('H',"hole.png"),
	GRASS('#',"grass.png");

	private char ascii;
	private String imageSrc;
	
	private Symbol(char ascii,String imageSrc) {
		this.ascii = ascii;
		this.imageSrc = imageSrc;
	}
	
	public static Symbol getName(char ascii) {
		Symbol name = null;
		switch(ascii){
		case 'w':
			name = Symbol.BUNNY_WHITE;
			break;
		case 'W':
			name = Symbol.BUNNY_WHITE_HOLE;
			break;
		case 'g':
			name = Symbol.BUNNY_GRAY;
			break;
		case 'G':
			name = Symbol.BUNNY_GRAY_HOLE;
			break;
		case 'b':
			name = Symbol.BUNNY_BROWN;
			break;
		case 'B':
			name = Symbol.BUNNY_BROWN_HOLE;
			break;
		case '^':
			name = Symbol.FOX_HEAD_UP;
			break;
		case '⊥':
			name = Symbol.FOX_TAIL_UP;
			break;
		case '>':
			name = Symbol.FOX_HEAD_RIGHT;
			break;		
		case '⊢':
			name = Symbol.FOX_TAIL_RIGHT;
			break;
		case 'V':
			name = Symbol.FOX_HEAD_DOWN;
			break;
		case 'T':
			name = Symbol.FOX_TAIL_DOWN;
			break;
		case '<':
			name = Symbol.FOX_HEAD_LEFT;
			break;
		case '⊣':
			name = Symbol.FOX_TAIL_LEFT;
			break;
		case 'M':
			name = Symbol.MUSHROOM;
			break;
		case 'H':
			name = Symbol.HOLE;
			break;
		case '#':
			name = Symbol.GRASS;
			break;
		}
		return name;
	}
	
	public char getAscii() {
		return this.ascii;
	}
	
	public String getImageSrc() {
		return this.imageSrc;
	}
	@Override
	public String toString() {
		return ""+ascii;
	}
}
