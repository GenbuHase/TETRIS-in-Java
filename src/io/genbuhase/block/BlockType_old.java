package io.genbuhase.block;

import java.lang.reflect.InvocationTargetException;

public enum BlockType_old {
	I(Block_old.I.class),
	O(Block_old.O.class),
	T(Block_old.T.class),
	J(Block_old.J.class),
	L(Block_old.L.class),
	S(Block_old.S.class),
	Z(Block_old.Z.class),
	H(Block_old.H.class),
	Opt1(Block_old.Opt1.class),
	Opt2(Block_old.Opt2.class),
	Opt3(Block_old.Opt3.class),
	Opt4(Block_old.Opt4.class);
	
	
	
	private Class<?> __class;
	
	
	
	/**
	 * 
	 * @param classObj	各ブロックのクラス
	 */
	private BlockType_old (Class<?> classObj) {
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
	public Block_old getNewInstance () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return (Block_old) this.__class.getDeclaredConstructor().newInstance();
	}
	
	
	
	/**
	 * 登録されているBlockTypeの長さを返す
	 * 
	 * @return	BlockTypeの長さ
	 */
	public static int getLength () {
		return BlockType_old.values().length;
	}
}