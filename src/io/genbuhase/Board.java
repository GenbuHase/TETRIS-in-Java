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
	
	/** ボード全体の状態を保持するグリッド */
	private Cell[][] outputGrid;
	/** 固定済のブロックを保持するグリッド */
	private Cell[][] lockedGrid;
	/** 現在のブロックを保持するグリッド */
	private Cell[][] blockGrid;
	
	
	
	public Board (int width, int height) {
		this.width = width;
		this.height = height;
		
		this.outputGrid = new Cell[height + 1][width + 2];
		this.lockedGrid = new Cell[height][width];
		this.blockGrid = new Cell[height][width];
		
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
		
		return blockGrid[index[0]][index[1]];
	}
	
	public void setCell (int x, int y, Color color, char ch) {
		int[] index = convertCoordinateToIndex(x, y);
		
		blockGrid[index[0]][index[1]] = new Cell(color, ch);
	}
	
	public boolean isOccupied (int x, int y) {
		return true;
	}
	
	public int clearLines () {
		return 0;
	}
	
	
	
	/**
	 * ボードをコンソールに描画する
	 */
	public void render () {
		clearConsole();
		
		mergeGrid();
		
		for (int col = 0; col < outputGrid.length; col++) {
			for (int row = 0; row < outputGrid[col].length; row++) {
				System.out.print(outputGrid[col][row].getDisplayableCharacter());
			}
			
			if (col != outputGrid.length - 1) {
				System.out.println();
			}
		}
	}
	
	/**
	 * コンソールを消去する
	 */
	public void clearConsole () {
		System.out.print("\n".repeat(20));
	}
	
	/**
	 * 固定済グリッドとブロックグリッドを出力グリッドにマージする
	 */
	public void mergeGrid () {
		for (int col = 0; col < outputGrid.length; col++) {
			if (col != outputGrid.length - 1) {
				System.arraycopy(lockedGrid[col], 0, outputGrid[col], 1, width);
			}
		}
		
		for (int col = 0; col < blockGrid.length; col++) {
			for (int row = 0; row < blockGrid[col].length; row++) {
				if (blockGrid[col][row].getValue() == '*') {
					System.out.println(blockGrid[col][row]);
				}
			}
		}
	}
	
	/**
	 * ボードを初期化する
	 */
	public void reset () {
		resetOutputGrid();
		resetLockedGrid();
		resetBlockGrid();
	}
	
	/**
	 * OutputGridを初期化する
	 */
	public void resetOutputGrid () {
		for (int col = 0; col < outputGrid.length; col++) {
			if (col != outputGrid.length - 1) {
				Arrays.fill(outputGrid[col], new Cell());
				
				outputGrid[col][0] = new Cell('│');
				outputGrid[col][outputGrid[col].length - 1] = new Cell('│');
			} else {
				Arrays.fill(outputGrid[col], new Cell('￣'));
			}
		}
	}
	
	/**
	 * LockedGridを初期化する
	 */
	public void resetLockedGrid () {
		for (int col = 0; col < lockedGrid.length; col++) {
			Arrays.fill(lockedGrid[col], new Cell());
		}		
	}
	
	/**
	 * BlockGridを初期化する
	 */
	public void resetBlockGrid () {
		for (int col = 0; col < blockGrid.length; col++) {
			Arrays.fill(blockGrid[col], new Cell());
		}
	}
	
	
	
	private int[] convertIndexToCoordinate (int col, int row) {
		return new int[] { row, height - 1 - col };
	}
	
	private int[] convertCoordinateToIndex (int x, int y) {
		return new int[] { height - 1 - y, x };
	}
}
