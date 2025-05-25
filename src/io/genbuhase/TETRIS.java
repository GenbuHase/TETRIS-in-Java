package io.genbuhase;

import java.util.Random;
import java.util.Scanner;

import io.genbuhase.block.BlockManager;

public class TETRIS {
	private static enum GameState { START, RUNNING, PAUSED, GAMEOVER }
	
	private static final int WIDTH = 10;
	private static final int HEIGHT = 14;
	
	private final Board board = new Board(WIDTH, HEIGHT);
	private final BlockManager blockManager = new BlockManager(board);
	
	private boolean isGameover = false;
	
	
	
	public static void main (String[] args) {
		new TETRIS();
	}
	
	
	
	TETRIS () {
		blockManager.spawnBlock();
		
		render();
		
		handleInput();
	}
	
	
	
	public void start () {
		
	}
	
	public void pause () {
		
	}
	
	public void resume () {
		
	}
	
	public void gameover () {
		isGameover = true;
	}
	
	public void reset () {
		
	}
	
	
	
	public void render () {
		new Thread(new Runnable() {	
			@Override
			public void run () {
				while (!isGameover) {
					try {
						Thread.sleep(250);
						
						board.render();
						blockManager.render();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	private void handleInput () {
		Random randomizer = new Random();
		Scanner keyboardScanner = new Scanner(System.in);
		
		while (!isGameover) {
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
					break;
					
				case "R":				// ランダム移動
					blockManager.moveBlock(randomizer.nextInt(1, 11), randomizer.nextInt(1, 11));
					break;
					
				case "q":				// 終了
					keyboardScanner.close();
					gameover();
					
					break;
					
				default:
					System.out.println("Input WASD");
					break;
			}
		}
	}
	
	
	
	private void __test () {
		// Color.__printAllColors();
	}
}
