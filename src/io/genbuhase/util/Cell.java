package io.genbuhase.util;

public class Cell {
	private Color color;
	private char value;
	
	
	
	public Cell () {
		this(Color.Default);
	}
	
	public Cell (Color color) {
		this(color, ' ');
	}
	
	public Cell (char value) {
		this(Color.Default, value);
	}
	
	public Cell (Color color, char value) {
		this.color = color;
		this.value = value;
	}
	
	
	
	public char getValue () {
		return value;
	}
	
	
	
	@Override
	public String toString () {
		return getDisplayableCharacter();
	}
	
	
	
	public String getDisplayableCharacter () {
		return color.colorText(toDisplayableCharacter());
	}
	
	public String toDisplayableCharacter () {
		return String.valueOf(value).replace(" ", "　").replace("*", "■");
	}
}
