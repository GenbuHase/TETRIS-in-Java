package io.genbuhase.util;

public class Cell {
	private Color color;
	private char ch;
	
	
	
	public Cell () {
		this(Color.Default);
	}
	
	public Cell (Color color) {
		this(color, '　');
	}
	
	public Cell (Color color, char ch) {
		this.color = color;
		this.ch = ch;
	}
	
	
	
	@Override
	public String toString () {
		return getDisplayableCharacter();
	}
	
	
	
	public String getDisplayableCharacter () {
		return color.colorText(toDisplayableCharacter());
	}
	
	public String toDisplayableCharacter () {
		return String.valueOf(ch).replace(" ", "　").replace("*", "■");
	}
}