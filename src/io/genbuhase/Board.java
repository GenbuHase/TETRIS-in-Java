package io.genbuhase;

import java.util.Arrays;

import io.genbuhase.block.Block;
import io.genbuhase.util.Position;

public class Board {
	/* 
	 * (1,10)　　　　　　　　　　　　(10, 10)
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * 　　　│＊＊＊＊＊＊＊＊＊＊│
	 * (1, 1)￣￣￣￣￣￣￣￣￣￣￣￣(10, 1)
	 */
	
	
	
	/** 幅 */
	private int width;
	/** 高さ */
	private int height;
	
	/** コンソールに出力する文字列バッファ */
	protected char[][] grid;
	
	
	
	/**
	 * 幅width, 高さheightのBoardを生成
	 * 
	 * @param width		幅
	 * @param height	高さ
	 */
	public Board (int width, int height) {
		this.width = width;
		this.height = height;
		
		this.grid = new char[height + 1][width + 2];
	}
	
	
	
	public char getCell (int x, int y) {
		return '　';
	}
	
	public void setCell (int x, int y, char ch) {
		
	}
	
	public void clearCell (int x, int y) {
		this.setCell(x, y, '　');
	}
	
	
	
	public void clearLines () {
		
	}
	
	/**
	 * 指定された座標にブロックがあるかどうか返す
	 * 
	 * @param x
	 * @param y
	 * 
	 * @return
	 */
	public boolean isOccupied (int x, int y) {
		return true;
	}
	
	
	
	public void reset () {
		
	}
	
	public boolean isGameOver () {
		return true;
	}
	
	
	
	/**
	 * 描写準備
	 */
	@Deprecated
	public void init () {
		clear();
		
		initBase();
		initBlocks();
	}
	
	/**
	 * 初期台の情報をbuffersに書き込む
	 */
	@Deprecated
	public void initBase () {
		for (int col = 0; col < grid.length; col++) {
			/*
			 * buffersのすべての文字を埋める処理。
			 * 最終行のみ'￣'で一行埋め、それ以外の場合は'　'で一行埋める。
			 */
			Arrays.fill(grid[col], col == grid.length - 1 ? '￣' : '　');
			
			// 最終行以外の場合
			if (col != grid.length - 1) {
				// 第1列に'｜'を代入
				grid[col][0] = '│';
				
				// 最終列に'｜'を代入
				grid[col][grid[col].length - 1] = '│';
			}
		}
	}
	
	/**
	 * 存在するブロックをbuffersに書き込む
	 */
	@Deprecated
	public void initBlocks () {
		for (Block block : TETRIS.blocks) {
			String[] shape = block.drawableShape;
			
			Position pos = block.getPos();
			
			for (int col = 0; col < block.height; col++) {
				for (int row = 0; row < block.width; row++) {
					// 1文字取得
					char part = shape[col].charAt(row);
					
					// '　'でない場合
					if (part != '　') {
						int[] indexedPos = pos.toIndexed();
						
						// TETRIS座標系から添字座標系に変換してbuffersに書き込む
						grid[indexedPos[0] - (shape.length - 1 - col)][indexedPos[1] + row] = part;
						
						System.out.println("buffers[%d][%d] = %s".formatted(indexedPos[0] - (block.height - 1 - col), indexedPos[1] + row, part));
					}
				}
			}
			
			System.out.println();
		}
	}
	
	/**
	 * buffersの内容をコンソールに出力する
	 */
	@Deprecated
	public void draw () {
		init();
		
		for (int i = 0; i < grid.length; i++) {
			// 最終行のみ改行文字を出力しない
			if (i == grid.length - 1) {
				System.out.print(grid[i]);
			} else {
				System.out.println(grid[i]);
			}
		}
	}
	
	/**
	 * コンソールの出力を消去する
	 */
	@Deprecated
	public void clear () {
		System.out.print("\n".repeat(20));
	}
}