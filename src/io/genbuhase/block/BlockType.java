package io.genbuhase.block;

import java.lang.reflect.InvocationTargetException;

public enum BlockType {
	I(Block.I.class),
	O(Block.O.class),
	T(Block.T.class),
	J(Block.J.class),
	L(Block.L.class),
	S(Block.S.class),
	Z(Block.Z.class),
	H(Block.H.class),
	Opt1(Block.Opt1.class),
	Opt2(Block.Opt2.class),
	Opt3(Block.Opt3.class),
	Opt4(Block.Opt4.class);
	
	
	
	public static BlockType[] values = BlockType.values();
	
	
	
	private Class<?> __class;
	
	/**
	 * 
	 * @param classObj	ブロッククラス
	 */
	private BlockType (Class<?> classObj) {
		this.__class = classObj;
	}
	
	
	
	/**
	 * ブロックのインスタンスを生成する
	 * 
	 * @return	ブロックのインスタンス
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Block newInstance () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return (Block) this.__class.getDeclaredConstructor().newInstance();
	}
	
	
	
	/**
	 * 登録されているBlockTypeの長さを返す
	 * 
	 * @return	BlockTypeの長さ
	 */
	public static int getLength () {
		return values.length;
	}
}
