package slopeTest;

import static org.junit.Assert.*;

import org.junit.Test;

import slope.Main;

public class MainTest {

	@Test
	public void testSolve() {
	}

	@Test
	public void testDiffH() {
		int actual = Main.diffH(3, 2);
		assertEquals(1, actual);
		actual = Main.diffH(2, 3);
		assertEquals(-1, actual);
	}

}
