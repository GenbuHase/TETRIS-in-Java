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
		currentBlock = nextBlock;
		
		try {
			nextBlock = generateBlockRandomly();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public boolean moveBlock (int dx, int dy) {
		// [TODO] 移動可能判定の実装
		currentBlock.move(dx, dy);
		
		return true;
	}
	
	public void dropBlock () {
		// [TODO] for文でmoveBlock(0, -1)を繰り返す処理の実装
	}
	
	public boolean rotateBlock () {
		return true;
	}
	
	public void lockBlock () {
		
	}
	
	public boolean isGameover () {
		return false;
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
	private Block generateBlockRandomly () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return BlockType.values[randomizer.nextInt(0, BlockType.getLength())].newInstance();
	}
	
	private boolean isCollided (Block block, int x, int y, int rotated) {
		return false;
	}
}