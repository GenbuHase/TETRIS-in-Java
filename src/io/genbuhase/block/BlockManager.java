package io.genbuhase.block;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import io.genbuhase.Board;

public class BlockManager extends ArrayList<Block> {
	private Board board;
	private Block currentBlock;
	private Block nextBlock;
	
	private Random randomizer = new Random();
	
	
	
	public BlockManager (Board board) {
		super();
		
		this.board = board;
	}
	
	
	
	/**
	 * 新たにブロックを生成する
	 */
	public void spawnBlock () {
		
	}
	
	/**
	 * ブロックオブジェクトをランダムに生成して返す
	 * 
	 * @return	ランダムに生成されたブロックオブジェクト
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private Block generateRandomBlock () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		BlockType[] blockTypes = BlockType.values();
		return (Block) blockTypes[randomizer.nextInt(0, blockTypes.length)].getNewInstance();
	}
	
	
	
	/**
	 * 現在のブロックを指定された分だけ動かす
	 * 
	 * @param dx	x方向への変位
	 * @param dy	y方向への変位
	 * 
	 * @return		移動可否
	 */
	public boolean moveBlock (int dx, int dy) {
		return true;
	}
	
	/**
	 * 現在のブロックを落下させる
	 */
	public void dropBlock () {
		
	}
	
	/**
	 * 現在のブロックを時計回りに回転させる
	 * 
	 * @return
	 */
	public boolean rotateBlock () {
		return true;
	}
	
	/**
	 * 現在のブロックを固定する
	 */
	public void lockBlock () {
		
	}
	
	
	
	public boolean isCollided (Block block) {
		return true;
	}
	
	
	
	/*public ArrayList<Position> getOccupiedPositions () {
		ArrayList<Position> occupiedPositions = new ArrayList<>();
		
		this.forEach(block -> {
			occupiedPositions.add(block.getPos());
		});
		
		return occupiedPositions;
	}*/
}