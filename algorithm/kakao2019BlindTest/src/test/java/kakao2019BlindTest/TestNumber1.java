package kakao2019BlindTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNumber1 {

	@Test
	public void testSolution() {
		String s = "aabbaccc";
		
		int expected = 7;
		assertEquals(expected, Solution1.solution(s));
	}

}
