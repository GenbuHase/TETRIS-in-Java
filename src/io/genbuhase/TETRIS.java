package io.genbuhase;

import java.util.Random;
import java.util.Scanner;

import io.genbuhase.block.BlockManager;
import io.genbuhase.util.Color;

public class TETRIS {
	private static enum GameState { START, RUNNING, PAUSED, GAMEOVER }
	
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	
	private static final Board board = new Board(WIDTH, HEIGHT);
	private static final BlockManager blockManager = new BlockManager(board);
	
	
	
	public static void main (String[] args) {
		board.setCell(0, 0, Color.Green, '■');
		board.setCell(1, 0, Color.Green, '■');
		board.setCell(2, 0, Color.Green, '■');
		board.setCell(2, 1, Color.Green, '■');
		
		board.render();
		
		return;
	}
	
	
	
	public void start () {
		
	}
	
	public void pause () {
		
	}
	
	public void resume () {
		
	}
	
	public void gameover () {
		
	}
	
	public void reset () {
		
	}
	
	
	
	public void render () {
		
	}
	
	private void handleInput () {
		Random randomizer = new Random();
		Scanner keyboardScanner = new Scanner(System.in);
		
		while (true) {
			String input = keyboardScanner.next();
			
			switch (input) {
				case "w":				// ↑
					blockManager.moveBlock(0, 1);
					break;
					
				case "s":				// ↓
					blockManager.moveBlock(0, -1);
					break;
					
				case "a":				// ←
					blockManager.moveBlock(-1, 0);
					break;
					
				case "d":				// →
					blockManager.moveBlock(1, 0);
					break;
					
				case "D":				// 落下
					blockManager.dropBlock();
					break;
					
				case "r":				// 回転
					blockManager.rotateBlock();
					System.out.println(blockManager.getCurrentBlock());
					
					break;
					
				case "R":				// ランダム移動
					blockManager.moveBlock(randomizer.nextInt(1, 11), randomizer.nextInt(1, 11));
					break;
					
				case "q":				// 終了
					keyboardScanner.close();
					System.exit(0);
					
					break;
					
				default:
					System.out.println("Input WASD");
					break;
			}
			
			/*if (!block.isSticked()) {
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
			}*/
		}
	}
	
	
	
	private void __test () {
		// Color.__printAllColors();
	}
}