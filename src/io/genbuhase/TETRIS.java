package io.genbuhase;

import io.genbuhase.block.Block;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
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
	protected static Canvas canvas = new Canvas(WIDTH, HEIGHT);
	
	/** 存在中のブロックを格納する配列 */
	protected static ArrayList<Block> blocks = new ArrayList<>();
	
	
	
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
	public static void appendBlock (Block block) {
		blocks.add(block);
		
		canvas.draw();
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
		
		canvas.draw();
	}
	
	/**
	 * 指定されたブロックを消去する
	 * 
	 * @param block		消去するブロック
	 */
	public static void dismissBlock (Block block) {
		blocks.remove(block);
		
		canvas.draw();
	}
	
	/**
	 * 指定されたブロックに対して操作を受け付ける
	 * 
	 * @param block		操作するブロック
	 */
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
					canvas.draw();
					
					break;
					
				case "s":				// ↓
					block.move(0, -1);
					canvas.draw();
					
					break;
					
				case "a":				// ←
					block.move(-1, 0);
					canvas.draw();
					
					break;
					
				case "d":				// →
					block.move(1, 0);
					canvas.draw();
					
					break;
					
				case "D":				// 落下
					block.drop();
					canvas.draw();
					
					break;
					
				case "r":				// 回転
					block.rotate();
					System.out.println(block.direction);
					
					canvas.draw();
					break;
					
				case "R":				// ランダム移動
					block.moveTo(randomizer.nextInt(1, 11), randomizer.nextInt(1, 11));
					
					canvas.draw();
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