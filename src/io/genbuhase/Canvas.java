package io.genbuhase;

import java.util.Arrays;

public class Canvas {
	/* 
	 * (1,10)　　　　　　　　　　(10, 10)
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * 　　　＊＊＊＊＊＊＊＊＊＊
	 * (1, 1)￣￣￣￣￣￣￣￣￣￣(10, 10)
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
		this.buffers = new char[width + 1][height + 2];
	}
	
	
	
	/**
	 * bufferに現在のブロックの要素を書き込む
	 */
	public void init () {
		clear();
		
		// bufferを初期化
		for (int col = 0; col < buffers.length; col++) {
			Arrays.fill(buffers[col], col == buffers.length - 1 ? '￣' : '　');
			
			if (col != buffers.length - 1) {
				buffers[col][0] = '│';
				buffers[col][buffers[col].length - 1] = '│';
			}
		}
		
		// 各ブロックの要素を書き込む
		for (Block block : TETRIS.blocks) {
			String[] shape = block.getDrawableShape();
			
			int[] pos = block.getPos();
			int x = pos[0];
			int y = pos[1];
			
			for (int col = 0; col < shape.length; col++) {
				for (int row = 0; row < shape[col].length(); row++) {
					char part = shape[col].charAt(row);
					
					if (part != '　') {
						buffers[TETRIS.HEIGHT - y - (shape.length - 1 - col)][x + row] = part;
					}
				}
			}
		}
		
		draw();
	}
	
	/**
	 * コンソールの出力を消去する
	 */
	public void clear () {
		System.out.print("\n".repeat(20));
	}
	
	/**
	 * buffersの内容をコンソールに出力する
	 */
	public void draw () {
		for (int i = 0; i < buffers.length; i++) {
			if (i == buffers.length - 1) {
				System.out.print(buffers[i]);
			} else {
				System.out.println(buffers[i]);
			}
		}
	}
}