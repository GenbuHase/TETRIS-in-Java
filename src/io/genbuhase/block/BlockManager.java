package io.genbuhase.block;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import io.genbuhase.Board;

public class BlockManager {
	private final Board board;
	private final Random randomizer = new Random();
	
	private Block currentBlock;
	private Block nextBlock;
	
	
	
	public BlockManager (Board board) {
		this.board = board;
	}
	
	
	
	public Block getCurrentBlock () {
		return currentBlock;
	}
	
	public Block getNextBlock () {
		return nextBlock;
	}
	
	
	
	public void spawnBlock () {
		if (nextBlock == null) {
			nextBlock = generateBlockRandomly();
		}
		
		currentBlock = nextBlock;
		
		nextBlock = generateBlockRandomly();
	}
	
	public boolean moveBlock (int dx, int dy) {
		// TODO: 移動可能判定の実装
		currentBlock.move(dx, dy);
		
		return true;
	}
	
	public void dropBlock () {
		// TODO: for文でmoveBlock(0, -1)を繰り返す処理の実装
	}
	
	public boolean rotateBlock () {
		currentBlock.rotate();
		return true;
	}
	
	public void lockBlock () {
		
	}
	
	public boolean isGameover () {
		return false;
	}
	
	public void render () {
		String[] shape = currentBlock.getShape();
		
		for (int col = 0; col < shape.length; col++) {
			for (int row = 0; row < shape[col].length(); row++) {
				if (shape[col].charAt(row) != ' ') {
					board.setCell(currentBlock.getX() + row, currentBlock.getY() + shape.length - 1 - col, currentBlock.getColor(), shape[col].charAt(row));
				}
			}
		}
	}
	
	
	
	/**
	 * ランダムなブロックを生成する
	 * 
	 * @return	ランダムに生成されたブロック
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private Block generateBlockRandomly () {
		try {
			return BlockType.values[randomizer.nextInt(0, BlockType.getLength())].newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private boolean isCollided (Block block, int x, int y, int rotated) {
		return false;
	}
}
