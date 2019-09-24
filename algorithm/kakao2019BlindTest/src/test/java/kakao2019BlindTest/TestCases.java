package kakao2019BlindTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCases {

	//@Test
	public void testSolution() {
		String s = "ababcdcdababcdcd";
		
		int expected = 9;
		assertEquals(expected, Solution1.solution(s));
	}
	//@Test
	public void testSolution1() {
		String s = "aaaaaaaaaaaaaa";
		
		int expected = 15;
		assertEquals(expected, Solution1.solution(s));
	}
	//@Test
	public void testSolution2() {
		String s = "abcabcdede";
		
		int expected = 8;
		assertEquals(expected, Solution1.solution(s));
	}
	//@Test
	public void testSolution3() {
		String s = "abcabcabcabcdededededede";
		
		int expected = 14;
		assertEquals(expected, Solution1.solution(s));
	}
	//@Test
	public void testSolution5() {
		String s = ")(";
		
		String expected = "()";
		assertEquals(expected, Solution2.solution(s));
	}
	//@Test
	public void testSolution4() {
		String s = "(()))(((()";
		
		String expected = "()(())()";
		assertEquals(expected, Solution2.solution(s));
	}
	
	@Test
	public void testSolution6() {
		String[] s = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		int[] expected = {3, 2, 4, 1, 0};
		assertEquals(expected, Solution4.solution(s, queries));
	}
	
}
