package harvestHoney;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class harvestHoneyTest {
	static List<Integer> honeys = new ArrayList<Integer>();
	@Before
	public void init() {
		honeys = Arrays.asList(3, 6, 7, 2, 3);
	}

	@Test
	public void filterHoneyAmount() {
		Collections.sort(honeys);
		Collections.reverse(honeys);
		System.out.println(honeys);
	}

}
