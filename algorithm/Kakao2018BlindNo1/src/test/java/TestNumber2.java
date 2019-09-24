import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class TestNumber2 {

	//@Test
	public void testSolution() {
		int N = 5;
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		
		int[] expecteds = {3, 4, 2, 1, 5};
		Assert.assertArrayEquals(expecteds , Number2.solution(N, stages));
	}
	
	//@Test
	public void testSolution2() {
		int N = 4;
		int[] stages = {4, 4, 4, 4, 4};
		
		int[] expecteds = {4, 1, 2, 3};
		Assert.assertArrayEquals(expecteds , Number2.solution(N, stages));
	}
	//@Test
	public void testSolution3() {
		int N = 4;
		int[] stages = {6, 6, 6, 6, 6};
		
		int[] expecteds = {1, 2, 3, 4};
		Assert.assertArrayEquals(expecteds , Number2.solution(N, stages));
	}
	
	//@Test
	public void testSolution4() {
		int N = 5;
		int[] stages = {2, 2, 6, 2, 4, 3, 3, 5};
		
		int[] expecteds = {5, 3, 4, 2, 1};
		Assert.assertArrayEquals(expecteds , Number2.solution(N, stages));
	}
	
	//@Test
	public void testSolution5() {
		int N = 3;
		int[] stages = {1, 1, 1, 2, 2, 2};
		
		int[] expecteds = {1, 2, 3};
		Assert.assertArrayEquals(expecteds , Number2.solution(N, stages));
	}
	
	//@Test
	public void testPq() {
		int N = 5;
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		
		int[] expecteds = {1, 2, 2, 2, 3, 3, 4, 6};
		Assert.assertArrayEquals(expecteds , Number2.solution(N, stages));
	}
	
	//@Test
	public void testSolution3_1() {
		String[][] tc = {
				{"100","ryan","music","2"},
				{"200","apeach","math","2"},
				{"300","tube","computer","3"},
				{"400","con","computer","4"},
				{"500","muzi","music","3"},
				{"600","apeach","music","2"}
				};
		
		int expected = 2;
		assertEquals(expected, Number3.solution(tc));
		
	}

}
