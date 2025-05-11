package io.genbuhase;

import java.util.Arrays;

import io.genbuhase.block.Block;
import io.genbuhase.util.Position;

public class Canvas {
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
	
	
	
	/** Canvasの幅 */
	private int width;
	/** Canvasの高さ */
	private int height;
	
	/** コンソールに出力する文字列バッファ */
	protected char[][] buffers;
	
	
	
	/**
	 * 幅width, 高さheightのCanvasを生成
	 * 
	 * @param width		Canvasの幅
	 * @param height	Canvasの高さ
	 */
	public Canvas (int width, int height) {
		this.width = width;
		this.height = height;
		
		this.buffers = new char[height + 1][width + 2];
	}
	
	
	
	/**
	 * 描写準備
	 */
	public void init () {
		clear();
		
		initBase();
		initBlocks();
	}
	
	/**
	 * 初期台の情報をbuffersに書き込む
	 */
	public void initBase () {
		for (int col = 0; col < buffers.length; col++) {
			/*
			 * buffersのすべての文字を埋める処理。
			 * 最終行のみ'￣'で一行埋め、それ以外の場合は'　'で一行埋める。
			 */
			Arrays.fill(buffers[col], col == buffers.length - 1 ? '￣' : '　');
			
			// 最終行以外の場合
			if (col != buffers.length - 1) {
				// 第1列に'｜'を代入
				buffers[col][0] = '│';
				
				// 最終列に'｜'を代入
				buffers[col][buffers[col].length - 1] = '│';
			}
		}
	}
	
	/**
	 * 存在するブロックをbuffersに書き込む
	 */
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
						buffers[indexedPos[0] - (shape.length - 1 - col)][indexedPos[1] + row] = part;
						
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
	public void draw () {
		init();
		
		for (int i = 0; i < buffers.length; i++) {
			// 最終行のみ改行文字を出力しない
			if (i == buffers.length - 1) {
				System.out.print(buffers[i]);
			} else {
				System.out.println(buffers[i]);
			}
		}
	}
	
	/**
	 * コンソールの出力を消去する
	 */
	public void clear () {
		System.out.print("\n".repeat(20));
	}
}