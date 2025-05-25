package io.genbuhase.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.genbuhase.block.Block;

public class BlockTest {
	private static String[][] EXPECTED_SHAPES = new String[][] {
		{
			"*****",
			"  *  ",
			" *   ",
			"    *"
		},
		
		{
			"   *",
			" * *",
			"  **",
			"   *",
			"*  *"
		},
		
		{
			"*    ",
			"   * ",
			"  *  ",
			"*****"
		},
		
		{
			"*  *",
			"*   ",
			"**  ",
			"* * ",
			"*   "
		}
	};
	
	
	
	private Block block;
	
	
	
	@BeforeEach
	public void setup () {
		/*block = new Block(
			Color.Orange,
			
			"*****",
			"  *  ",
			" *   ",
			"    *"
		);*/
	}
	
	
	
	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	public void getRotatedShape (int direction) {
		// String[] actual = block.__getRotatedShape(direction);
		
		// assertArrayEquals(EXPECTED_SHAPES[direction], actual);
	}
}
