package io.genbuhase.block;

import io.genbuhase.util.Color;
import java.util.Arrays;

public class Block {
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
	/** ブロックの未回転時の形状 */
	private final String[] originalShape;
	
	/** X座標 */
	private int x;
	/** Y座標 */
	private int y;
	/** 幅 */
	private int width;
	/** 高さ */
	private int height;
	/** 回転状態 */
	private int rotated;
	/** 回転状態を考慮した形状 */
	private String[] shape;
	
	
	
	private Block (String... shape) {
		this(Color.Cyan, shape);
	}
	
	private Block (Color color, String... shape) {
		this.color = color;
		this.originalShape = this.shape = shape;
		
		this.width = __getExactWidth();
		this.height = __getExactHeight();
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
	
	public String[] getCells () {
		return shape;
	}
	
	
	
	/**
	 * ブロックの正確な幅を取得する
	 * 
	 * @return	正確な幅
	 */
	private int __getExactWidth () {
		int width = 0;
		int exactWidth = 0;
		
		String matchedAddress = "";
		int[] matchedAddresses;
		
		for (String col : shape) {
			if (width < col.length()) width = col.length();
		}
		
		matchedAddresses = new int[width];
		
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
		int height = shape.length;
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
	
	
	
	@Override
	public String toString () {
		return String.format("Block [color=%s, shape=%s, x=%s, y=%s, width=%s, height=%s, rotated=%s, cells=%s]", color, Arrays.toString(shape), x, y, width, height, rotated, Arrays.toString(shape));
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
		// [TODO] 回転処理の実装
		
		this.width = __getExactWidth();
		this.height = __getExactHeight();
	}
}