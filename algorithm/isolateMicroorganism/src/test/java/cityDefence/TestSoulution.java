package cityDefence;

import org.junit.Test;

public class TestSoulution {
	static int N = 15;
	@Test
	public void test() {
		System.out.println(getDist(3, 8, 14*15 + 2));
	}
	
	private static double getDist(int x, int y, int houseXY) {
		int houseX = houseXY / N;
		int houseY = houseXY % N;
		return Math.abs(x - houseX) + Math.abs(y -houseY);
	}
}
