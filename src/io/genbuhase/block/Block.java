package io.genbuhase.block;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import io.genbuhase.TETRIS;
import io.genbuhase.util.Position;

public class Block {
	public static class I extends Block {
		public I () {
			super(
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
				"**",
				"**"
			);
		}
	}
	
	public static class T extends Block {
		public T () {
			super(
				"   ",
				" * ",
				"***"
			);
		}
	}
	
	public static class J extends Block {
		public J () {
			super(
				"   ",
				"*  ",
				"***"
			);
		}
	}
	
	public static class L extends Block {
		public L () {
			super(
				"   ",
				"***",
				"*  "
			);
		}
	}
	
	public static class S extends Block {
		public S () {
			super(
				"   ",
				" **",
				"** "
			);
		}
	}
	
	public static class Z extends Block {
		public Z () {
			super(
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
	
	
	
	/**
	 * ブロックの形状
	 */
	public final String[] shape;
	
	/**
	 * 描画可能なshape
	 */
	public final String[] drawableShape;
	
	/**
	 * ブロックの幅
	 */
	public final int width;
	
	/**
	 * ブロックの高さ
	 */
	public final int height;
	
	
	
	/**
	 * ブロックの位置(左下を(1, 1)、右上を(10, 10)とする)
	 */
	private Position pos = new Position(1, 1);
	
	/**
	 * ブロックの向いている方向(北: 0, 東: 1, 南: 2, 西: 3)
	 */
	public int direction = 0;
	
	/**
	 * ブロックの活性化状態
	 */
	public boolean isActive = true;
	
	
	
	/**
	 * 
	 * @param shape		ブロックの形状
	 */
	public Block (String... shape) {
		this.shape = shape;
		this.drawableShape = Block.toDrawable(shape);
		
		this.width = this.__getWidth();
		this.height = this.__getHeight();
	}
	
	
		
	private int __getWidth () {
		int width = 0;
		int sanitizedWidth = 0;
		
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
		
		sanitizedWidth = matchedAddress.lastIndexOf("1") - matchedAddress.indexOf("1") + 1;
		
		return width;
	}
	
	private int __getHeight () {
		int height = shape.length;
		int sanitizedHeight = 0;
		
		String matchedAddress = "";
		int[] matchedAddresses = new int[height];
		
		for (int col = 0; col < height; col++) {
			if (shape[col].indexOf("*") != -1) matchedAddresses[col] = 1;
		}
		
		for (int address : matchedAddresses) {
			matchedAddress = matchedAddress.concat(Integer.toString(address));
		}
		
		sanitizedHeight = matchedAddress.lastIndexOf("1") - matchedAddress.indexOf("1") + 1;
		
		return height;
	}
	
	

	public Position getPos () {
		return pos;
	}
	
	public void setPos (int[] pos) {
		if (!(pos.length == 1 || pos.length == 2)) {
			throw new IllegalArgumentException();
		}
		
		this.setPos(pos[0], pos[1]);
	}
	
	public void setPos (int x, int y) {
		this.setPosX(x);
		this.setPosY(y);
	}
	
	public void setPosX (int x) {
		if (x < 1) {
			pos.x = 1;
		} else if (TETRIS.WIDTH < x + this.width - 1) {
			pos.x = TETRIS.WIDTH - this.width + 1;
		} else {
			pos.x = x;
		}
	}
	
	public void setPosY (int y) {
		if (y < 1) {
			pos.y = 1;
		} else if (TETRIS.HEIGHT < y + this.height - 1) {
			pos.y = TETRIS.HEIGHT - this.height + 1;
		} else {
			pos.y = y;
		}
	}
	
	
	
	@Override
	public String toString () {
		return String.join("\n", shape);
	}
	
	
	
	/**
	 * 指定された変位のぶんだけブロックを動かす
	 * 
	 * @param dx	x方向への変位
	 * @param dy	y方向への変位
	 */
	public void move (int dx, int dy) {
		Position pos = this.getPos();
		
		this.setPos(pos.x + dx, pos.y + dy);
	}
	
	/**
	 * 指定された座標にブロックを動かす
	 * 
	 * @param x		移動後のx座標
	 * @param y		移動後のy座標
	 */
	public void moveTo (int x, int y) {
		this.setPos(x, y);
	}
	
	/**
	 * ブロックを落下させる
	 */
	public void drop () {
		this.setPosY(1);
	}
	
	/**
	 * ブロックを回転させる
	 */
	public void rotate () {
		this.direction = (this.direction == 3 ? 0 : this.direction + 1);
	}
	
	/**
	 * 移動可能か否かを返す
	 * 
	 * @return
	 */
	public boolean isSticked () {
		if (1 < pos.y) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * ブロックを描画する
	 */
	@Deprecated
	public void draw () {
		for (int col = shape.length - 1; col == 0; col--) {
			
		}
		
		System.out.println(this.toString());
	}
	
	
	
	/**
	 * 回転状況を反映したブロック形状を返す
	 * 
	 * @return	回転後のブロック形状
	 */
	public String[] getRotatedShape () {
		String[] rotated = shape;
		
		return rotated;
	}
	
	/**
	 * ブロックのインスタンスをランダムに返す
	 * 
	 * @return	ランダムなブロックのインスタンス
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Block generateRandomBlock () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Random randomizer = new Random();
		
		BlockType[] blockTypes = BlockType.values();
		return (Block) blockTypes[randomizer.nextInt(0, blockTypes.length)].getNewInstance();
	}
		
	/**
	 * 指定されたshapeを描画可能体にして返す
	 * 
	 * @param shape		ブロックの形状
	 * @return			描画可能体に変換されたshape
	 */
	public static String[] toDrawable (String[] shape) {
		String[] drawable = new String[shape.length];
		
		for (int i = 0; i < shape.length; i++) {
			drawable[i] = shape[i].replace(" ", "　").replace("*", "■");
		}
		
		return drawable;
	}
}