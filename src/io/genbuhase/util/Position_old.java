package io.genbuhase.util;

import io.genbuhase.TETRIS_old;

/**
 * TETRIS座標系を取り扱うクラス
 */
public class Position_old {
	/** TETRIS座標系におけるX座標 */
	public int x;
	
	/** TETRIS座標系におけるY座標 */
	public int y;
	
	
	
	/**
	 * 
	 * @param position { X座標, Y座標 }
	 */
	public Position_old (int[] position) {
		this(position[0], position[1]);
	}
	
	/**
	 * 
	 * @param x			X座標
	 * @param y			Y座標
	 */
	public Position_old (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	
	@Override
	public String toString () {
		return "(%d, %d)".formatted(x, y);
	}
	
	
	
	/**
	 * xとyをint型の配列にして返す
	 * 
	 * @return	{ x, y }
	 */
	public int[] toArray () {
		int[] converted = { x, y };
		
		return converted;
	}
	
	/**
	 * TETRIS座標系から添字座標系に変換して返す
	 * 
	 * @return
	 */
	public int[] toIndexed () {
		int[] indexed = new int[2];
		
		indexed[1] = x;
		indexed[0] = TETRIS_old.HEIGHT - y;
		
		return indexed;
	}
	
	
	
	/**
	 * 与えられた添字座標系からTETRIS座標系を生成する
	 * 
	 * @param indexes
	 * @return
	 */
	public static Position_old fromIndex (int[] indexes) {
		return fromIndex(indexes[0], indexes[1]);
	}
	
	/**
	 * 与えられた添字座標系からTETRIS座標系を生成する
	 * 
	 * @param col
	 * @param row
	 * 
	 * @return
	 */
	public static Position_old fromIndex (int col, int row) {
		return new Position_old(row, TETRIS_old.HEIGHT - col);
	}
}