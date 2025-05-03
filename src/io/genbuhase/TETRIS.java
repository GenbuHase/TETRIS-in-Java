package io.genbuhase;

import java.util.ArrayList;
import java.util.Scanner;

public class TETRIS {
	/** Canvasの幅 */
	public static final int WIDTH = 10;
	/** Canvasの高さ */
	public static final int HEIGHT = 10;
	
	private static Canvas canvas = new Canvas(WIDTH, HEIGHT);
	protected static ArrayList<Block> blocks = new ArrayList<>();
	
	
	
	public static void main (String[] args) {
		/*Random randomizer = new Random();
		
		while (true) {
			Block i = new Block.I();
			Block o = new Block.O();
			Block t = new Block.T();
			Block j = new Block.J();
			Block l = new Block.L();
			Block s = new Block.S();
			Block z = new Block.Z();
			Block h = new Block.H();
			
			appendBlock(i, 1 + randomizer.nextInt(7), 1 + randomizer.nextInt(9));
			i.drop();
			
			appendBlock(o, 1 + randomizer.nextInt(7), 1 + randomizer.nextInt(9));
			o.drop();
			
			appendBlock(t, 1 + randomizer.nextInt(7), 1 + randomizer.nextInt(9));
			t.drop();
			
			appendBlock(j, 1 + randomizer.nextInt(7), 1 + randomizer.nextInt(9));
			j.drop();
			
			appendBlock(l, 1 + randomizer.nextInt(7), 1 + randomizer.nextInt(9));
			l.drop();
			
			appendBlock(s, 1 + randomizer.nextInt(7), 1 + randomizer.nextInt(9));
			s.drop();
			
			appendBlock(z, 1 + randomizer.nextInt(7), 1 + randomizer.nextInt(9));
			z.drop();
			
			appendBlock(h, 1 + randomizer.nextInt(7), 1 + randomizer.nextInt(9));
			h.drop();
			
			break;
		}*/
		
		/*appendBlock(new Block.I(), 4, 4);
		appendBlock(new Block.O(), 1, 1);
		appendBlock(new Block.T(), 8, 1);
		appendBlock(new Block.J(), 1, 5);
		appendBlock(new Block.L(), 8, 5);
		appendBlock(new Block.S(), 4, 6);
		appendBlock(new Block.Z(), 4, 1);
		appendBlock(new Block.H(), 8, 2);*/
		
		Block block = new Block.H();
		appendBlock(block);
		
		handle(block);
	}
	
	/**
	 * 指定されたブロックを追加する
	 * 
	 * @param block		追加するブロック
	 */
	public static void appendBlock (Block block) {
		blocks.add(block);
		
		canvas.init();
	}
	
	/**
	 * 指定されたブロックを(x, y)に追加する
	 * 
	 * @param block		追加するブロック
	 * @param x			X座標
	 * @param y			Y座標
	 */
	public static void appendBlock (Block block, int x, int y) {
		blocks.add(block);
		block.moveTo(x, y);
		
		canvas.init();
	}
	
	/**
	 * 指定されたブロックに対して操作を受け付ける
	 * 
	 * @param block		操作するブロック
	 */
	public static void handle (Block block) {
		Scanner input = new Scanner(System.in);
		
		while (true) {
			String str = input.next();
			
			switch (str) {
				case "w":				// ↑
					block.move(0, 1);
					canvas.init();
					
					break;
					
				case "s":				// ↓
					block.move(0, -1);
					canvas.init();
					
					break;
					
				case "a":				// ←
					block.move(-1, 0);
					canvas.init();
					
					break;
					
				case "d":				// →
					block.move(1, 0);
					canvas.init();
					
					break;
					
				case "D":				// 落下
					block.drop();
					canvas.init();
					
					break;
					
				case "r":				// 回転
					block.rotate();
					System.out.println(block.direction);
					
					canvas.init();
					break;
					
				default:
					System.out.println("Input WASD");
					break;
			}
		}
	}
}
