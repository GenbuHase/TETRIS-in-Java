package io.genbuhase;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;

import io.genbuhase.block.Block;
import io.genbuhase.block.BlockManager;

/*
 * ブロック着地後はブロックとして保全する理由はない。
 * 消去するときにブロック単位で消去するわけではない。
 * 
 * [TODO] ブロックの積み上げ機能
 * [TODO] ブロックの回転機能
 */
public class TETRIS {
	/** Canvasの幅 */
	public static final int WIDTH = 10;
	/** Canvasの高さ */
	public static final int HEIGHT = 10;
	
	/** キャンバス */
	protected static Board board = new Board(WIDTH, HEIGHT);
	
	/** 存在中のブロックを格納する配列 */
	protected static BlockManager blocks = new BlockManager(board);
	
	
	
	public static void main (String[] args) {
		Test();
		
		
		
		Random randomizer = new Random();
		
		while (true) {
			if (blocks.isEmpty() || !blocks.getLast().isActive) {
				try {
					Block block = Block.generateRandomBlock();
					appendBlock(block, randomizer.nextInt(1, WIDTH + 1), HEIGHT);
					
					handleBlock(block);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	/**
	 * 指定されたブロックを追加する
	 * 
	 * @param block		追加するブロック
	 */
	@Deprecated
	public static void appendBlock (Block block) {
		blocks.add(block);
		
		board.draw();
	}
	
	/**
	 * 指定されたブロックを(x, y)に追加する
	 * 
	 * @param block		追加するブロック
	 * @param x			X座標
	 * @param y			Y座標
	 */
	@Deprecated
	public static void appendBlock (Block block, int x, int y) {
		blocks.add(block);
		block.moveTo(x, y);
		
		board.draw();
	}
	
	/**
	 * 指定されたブロックを消去する
	 * 
	 * @param block		消去するブロック
	 */
	@Deprecated
	public static void dismissBlock (Block block) {
		blocks.remove(block);
		
		board.draw();
	}
	
	/**
	 * 指定されたブロックに対して操作を受け付ける
	 * 
	 * @param block		操作するブロック
	 */
	@Deprecated
	public static void handleBlock (Block block) {
		Random randomizer = new Random();
		Scanner input = new Scanner(System.in);
		
		while (true) {
			if (!block.isSticked()) {
				block.isActive = false;
				break;
			}
			
			String str = input.next();
			
			switch (str) {
				case "w":				// ↑
					block.move(0, 1);
					board.draw();
					
					break;
					
				case "s":				// ↓
					block.move(0, -1);
					board.draw();
					
					break;
					
				case "a":				// ←
					block.move(-1, 0);
					board.draw();
					
					break;
					
				case "d":				// →
					block.move(1, 0);
					board.draw();
					
					break;
					
				case "D":				// 落下
					block.drop();
					board.draw();
					
					break;
					
				case "r":				// 回転
					block.rotate();
					System.out.println(block.rotationState);
					
					board.draw();
					break;
					
				case "R":				// ランダム移動
					block.moveTo(randomizer.nextInt(1, 11), randomizer.nextInt(1, 11));
					
					board.draw();
					break;
					
				case "q":				// 終了
					input.close();
					
					System.exit(0);
					break;
					
				default:
					System.out.println("Input WASD");
					break;
			}
		}
	}
	
	
	
	public static void Test () {
		
	}
}