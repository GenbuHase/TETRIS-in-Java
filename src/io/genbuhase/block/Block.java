package io.genbuhase.block;

import java.util.Arrays;

import io.genbuhase.util.Color;

public class Block {
	// TODO: Directionを用いた実装
	private static enum Direction { North, East, South, West }
	
	
	
	public static class I extends Block {
		public I () {
			super(
				Color.Cyan,
				
				"    ",
				"    ",
				"    ",
				"****"
			);
		}
	}
	
	public static class O extends Block {
		public O () {
			super(
				Color.Yellow,
				
				"**",
				"**"
			);
		}
	}
	
	public static class T extends Block {
		public T () {
			super(
				Color.Purple,
				
				"   ",
				" * ",
				"***"
			);
		}
	}
	
	public static class J extends Block{
		public J () {
			super(
				Color.Blue,
				
				"   ",
				"*  ",
				"***"
			);
		}
	}
	
	public static class L extends Block {
		public L () {
			super(
				Color.Orange,
				
				"   ",
				"***",
				"*  "
			);
		}
	}
	
	public static class S extends Block {
		public S () {
			super(
				Color.Green,
				
				"   ",
				" **",
				"** "
			);
		}
	}
	
	public static class Z extends Block {
		public Z () {
			super(
				Color.Red,
				
				"   ",
				"** ",
				" **"
			);
		}
	}
	
	public static class H extends Block {
		public H () {
			super(
				"   ",
				"***",
				"* *"
			);
		}
	}
	
	public static class Opt1 extends Block {
		public Opt1 () {
			super(
				"   ",
				"***",
				"***"
			);
		}
	}
	
	public static class Opt2 extends Block {
		public Opt2 () {
			super(
				"***",	
				"***",
				"***"
			);
		}
	}
	
	public static class Opt3 extends Block {
		public Opt3 () {
			super(
				"* *",
				" * ",
				"* *"
			);
		}
	}
	
	public static class Opt4 extends Block {
		public Opt4 () {
			super(
				"*   ",
				" *  ",
				"  * ",
				"   *"
			);
		}
	}
	
	public static class Opt5 extends Block {
		public Opt5 () {
			super(
				"*   ",
				"            ",
				"        ",
				"   *            "
			);
		}
	}
	
	
	
	/** ブロックの色 */
	private final Color color;
	/** ブロックの未回転状態の形状 */
	private final String[] originalShape;
	/** ブロックの各回転状態の形状*/
	private final String[][] rotatedShapes = new String[4][];
	
	/** X座標 */
	private int x;
	/** Y座標 */
	private int y;
	/** 幅 */
	private int width;
	/** 高さ */
	private int height;
	/** 厳密な幅 */
	private int exactWidth;
	/** 厳密な高さ */
	private int exactHeight;
	/** 回転状態 */
	private int direction;
	/** 回転状態を考慮した形状 */
	private String[] shape;
	
	
	
	public Block (String... shape) {
		this(Color.Cyan, shape);
	}
	
	public Block (Color color, String... shape) {
		this.color = color;
		this.originalShape = this.shape = shape;
		
		this.width = __getWidth();
		this.height = __getHeight();
		this.exactWidth = __getExactWidth();
		this.exactHeight = __getExactHeight();
		
		for (int direction = 0; direction < 4; direction++) {
			this.rotatedShapes[direction] = __getRotatedShape(direction);
		}
	}
	
	
	
	public Color getColor () {
		return color;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}
	
	public int getExactWidth () {
		return exactWidth;
	}
	
	public int getExactHeight () {
		return exactHeight;
	}
	
	public String[] getShape () {
		return shape;
	}
	
	
	
	@Override
	public String toString () {
		return String.format("Block [color=%s, originalShape=%s, rotatedShapes=%s, x=%s, y=%s, width=%s, height=%s, exactWidth=%s, exactHeight=%s, direction=%s, shape=%s]", color, Arrays.toString(originalShape), Arrays.toString(rotatedShapes), x, y, width, height, exactWidth, exactHeight, direction, Arrays.toString(shape));
	}
	
	
	
	/**
	 * ブロックを指定されたマスだけ移動する
	 * 
	 * @param dx	X座標の変位
	 * @param dy	Y座標の変位
	 */
	public void move (int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	/**
	 * ブロックを指定されたマスに移動する
	 * 
	 * @param x		X座標
	 * @param y		Y座標
	 */
	public void moveTo (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * ブロックを時計回りに90°回転する
	 */
	public void rotate () {
		rotateTo(3 <= direction  ? 0 : direction + 1);
	}
	
	/**
	 * ブロックを指定した方向に回転する
	 * 
	 * @param direction		
	 */
	public void rotateTo (int direction) {
		this.direction = direction;
		this.shape = rotatedShapes[direction];
		
		this.width = __getWidth();
		this.height = __getHeight();
		this.exactWidth = __getExactWidth();
		this.exactHeight = __getExactHeight();
	}
	
	
	
	/**
	 * ブロックの幅を取得する
	 * 
	 * @return	幅
	 */
	private int __getWidth () {
		int width = 0;
		
		for (String col : shape) {
			if (width < col.length()) width = col.length();
		}
		
		return width;
	}
	
	/**
	 * ブロックの高さを取得する
	 * 
	 * @return	高さ
	 */
	private int __getHeight () {
		return shape.length;
	}
	
	/**
	 * ブロックの正確な幅を取得する
	 * 
	 * @return	正確な幅
	 */
	private int __getExactWidth () {
		int width = __getWidth();
		int exactWidth = 0;
		
		String matchedAddress = "";
		int[] matchedAddresses = new int[width];
		
		for (String col : shape) {
			for (int i = 0; i < col.length(); i++) {
				if (col.charAt(i) == '*') matchedAddresses[i] = 1;
			}
		}
		
		for (int address : matchedAddresses) {
			matchedAddress = matchedAddress.concat(Integer.toString(address));
		}
		
		exactWidth = matchedAddress.lastIndexOf("1") - matchedAddress.indexOf("1") + 1;
		
		return exactWidth;
	}
	
	/**
	 * ブロックの正確な高さを取得する
	 * 
	 * @return	正確な高さ
	 */
	private int __getExactHeight () {
		int height = __getHeight();
		int exactHeight = 0;
		
		String matchedAddress = "";
		int[] matchedAddresses = new int[height];
		
		for (int col = 0; col < height; col++) {
			if (shape[col].indexOf("*") != -1) matchedAddresses[col] = 1;
		}
		
		for (int address : matchedAddresses) {
			matchedAddress = matchedAddress.concat(Integer.toString(address));
		}
		
		exactHeight = matchedAddress.lastIndexOf("1") - matchedAddress.indexOf("1") + 1;
		
		return exactHeight;
	}
	
	/**
	 * 指定された回転状態におけるブロックの形状を取得する
	 * 
	 * @param direction		回転状態
	 * 
	 * @return				回転状態を考慮したブロックの形状
	 */
	private String[] __getRotatedShape (int direction) {
		String[] rotatedShape;
		
		switch (direction) {
			case 0:
				return originalShape;
				
			case 1:
				rotatedShape = new String[width];
				Arrays.fill(rotatedShape, "");
				
				for (int col = 0; col < width; col++) {
					char[] buffer = new char[height];
					
					for (int row = 0; row < height; row++) {
						/*
						 * 00 01 02 03
						 * 10 11 12 13
						 * 20 21 22 23
						 * 30 31 32 33
						 * 40 41 42 43
						 * 
						 * 40 30 20 10 00
						 * 41 31 21 11 01
						 * 42 32 22 12 02
						 * 43 33 23 13 03
						 */
						buffer[row] = originalShape[height - 1 - row].charAt(col);
					}
					
					rotatedShape[col] = String.valueOf(buffer);
				}
				
				break;
				
			case 2:
				rotatedShape = new String[height];
				Arrays.fill(rotatedShape, "");
				
				for (int col = 0; col < height; col++) {
					char[] buffer = new char[width];
					
					for (int row = 0; row < width; row++) {
						/*
						 * 00 01 02 03
						 * 10 11 12 13
						 * 20 21 22 23
						 * 30 31 32 33
						 * 40 41 42 43
						 * 
						 * 43 42 41 40
						 * 33 32 31 30
						 * 23 22 21 20
						 * 13 12 11 10
						 * 03 02 01 00
						 */
						buffer[row] = originalShape[height - 1 - col].charAt(width - 1 - row);
					}
					
					rotatedShape[col] = String.valueOf(buffer);
				}
				
				break;
				
			case 3:
				rotatedShape = new String[width];
				Arrays.fill(rotatedShape, "");
				
				for (int col = 0; col < width; col++) {
					char[] buffer = new char[height];
					
					for (int row = 0; row < height; row++) {
						/*
						 * 00 01 02 03
						 * 10 11 12 13
						 * 20 21 22 23
						 * 30 31 32 33
						 * 40 41 42 43
						 * 
						 * 03 13 23 33 43
						 * 02 12 22 32 42
						 * 01 11 21 31 41
						 * 00 10 20 30 40
						 */
						buffer[row] = originalShape[row].charAt(width - 1 - col);
					}
					
					rotatedShape[col] = String.valueOf(buffer);
				}
				
				break;
				
			default:
				throw new IllegalArgumentException("Direction must be between 0-3.");
		}
		
		return rotatedShape;
	}
}
