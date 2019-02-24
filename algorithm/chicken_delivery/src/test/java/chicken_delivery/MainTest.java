package chicken_delivery;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		assertEquals(2, Main.calDistance(new Point(2,1), new Point(1,2)));
		assertEquals(7, Main.calDistance(new Point(2,1), new Point(5,5)));
	}
}
