package io.genbuhase;

import java.util.Arrays;

import io.genbuhase.util.Cell;
import io.genbuhase.util.Color;

public class Board {
	/* 
	 * (0, 9)　　　　　　　　　　　　(9, 9)
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * (0, 0)￣￣￣￣￣￣￣￣￣￣￣￣(9, 0)
	 */
	
	
	
	private final int width;
	private final int height;
	
	private Cell[][] grid;
	
	
	
	public Board (int width, int height) {
		this.width = width;
		this.height = height;
		
		this.grid = new Cell[width][height];
		
		reset();
	}
	
	
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}
	
	
	
	
	public Cell getCell (int x, int y) {
		int[] index = convertCoordinateToIndex(x, y);
		
		return grid[index[0]][index[1]];
	}
	
	public void setCell (int x, int y, Color color, char ch) {
		int[] index = convertCoordinateToIndex(x, y);
		
		grid[index[0]][index[1]] = new Cell(color, ch);
	}
	
	public boolean isOccupied (int x, int y) {
		return true;
	}
	
	public int clearLines () {
		return 0;
	}
	
	public void reset () {
		for (int col = 0; col < grid.length; col++) {
			Arrays.fill(grid[col], new Cell());
		}
	}
	
	public void clear () {
		System.out.print("\n".repeat(20));
	}
	
	public void render () {
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid[col].length; row++) {
				System.out.print(grid[col][row].getDisplayableCharacter());
			}
			
			if (col != grid.length - 1) {
				System.out.println();
			}
		}
	}
	
	
	
	private int[] convertIndexToCoordinate (int col, int row) {
		return new int[] { row, height - 1 - col };
	}
	
	private int[] convertCoordinateToIndex (int x, int y) {
		return new int[] { height - 1 - y, x };
	}
}