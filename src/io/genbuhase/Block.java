package io.genbuhase;

public class Block {
	public static class I extends Block {
		I () {
			super("****");
		}
	}
	
	public static class O extends Block {
		O () {
			super(
				"**",
				"**"
			);
		}
	}
	
	public static class T extends Block {
		T () {
			super(
				" * ",
				"***"
			);
		}
	}
	
	public static class J extends Block {
		J () {
			super(
				"*  ",
				"***"
			);
		}
	}
	
	public static class L extends Block {
		L () {
			super(
				"***",
				"*  "
			);
		}
	}
	
	public static class S extends Block {
		S () {
			super(
				" **",
				"** "
			);
		}
	}
	
	public static class Z extends Block {
		Z () {
			super(
				"** ",
				" **"
			);
		}
	}
	
	public static class H extends Block {
		H () {
			super(
				"***",
				"* *"
			);
		}
	}
	
	
	
	/**
	 * ブロックの形状
	 */
	private String[] shape;
	
	/**
	 * 描画用に変換したshape
	 */
	private String[] drawableShape;
	
	/**
	 * ブロックの位置(左下を(1, 1)、右上を(10, 10)とする)
	 */
	private int[] pos = { 1, 1 };
	
	/**
	 * ブロックの向いている方向(北: 0, 東: 1, 南: 2, 西: 3)
	 */
	public int direction = 0;
	
	
	
	Block (String... shape) {
		this.shape = shape;
		this.drawableShape = Block.toDrawable(shape);
	}
	
	
	
	@Override
	public String toString () {
		return String.join("\n", shape);
	}
	
	
	
	public void move (int dx, int dy) {
		this.pos[0] += dx;
		this.pos[1] += dy;
	}
	
	public void moveTo (int x, int y) {
		this.pos[0] = x;
		this.pos[1] = y;
	}
	
	public void drop () {
		this.pos[1] = 1;
	}
	
	public void rotate () {
		this.direction = (this.direction == 3 ? 0 : this.direction + 1);
	}
	
	public void draw () {
		for (int col = shape.length - 1; col == 0; col--) {
			
		}
		
		System.out.println(this.toString());
	}
	
	public static String[] toDrawable (String[] shape) {
		String[] drawable = new String[shape.length];
		
		for (int i = 0; i < shape.length; i++) {
			drawable[i] = shape[i].replace(" ", "　").replace("*", "■");
		}
		
		return drawable;
	}
	
	
	
	public String[] getDrawableShape () {
		return drawableShape;
	}

	public int[] getPos () {
		return pos;
	}
}