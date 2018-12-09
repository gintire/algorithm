import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		//int cases[][] = {}; 
		String[][] res = {{"L+", "owwbwwbww"}, {"L-", "rwwrwwrww"}};
		for(int i=0; i<3; i++)
			assertEquals(res[i][1], Main.solve1(res[i][0]));
	}

}
