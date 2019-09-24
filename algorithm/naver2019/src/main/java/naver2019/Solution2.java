package naver2019;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class Solution2 {

	private static long[] memo = new long[2000001];
	private static int memoIdx = 2;
	private static long MAX_VALUE = Long.parseLong("1000000000000");
	@Test
	public void test_cast2() {
		//assertEquals(2, solution(1));
		//assertEquals(6, solution(2));
		//assertEquals(20, solution(4));
		//assertEquals(60, solution(9));
		assertEquals(Long.parseLong("977394220056"), solution(1000000));
	}
	public static long solution(int n) {
		if(n==1) return 2;
		init();
		return memo[n-1];
	}
	public static void dfs(int idx, int length) {
		long thisValue = 1;
		if(length > idx-1) return;
		for(int i=0; i<length; i++) {
			thisValue *= idx - i;
			if(thisValue > MAX_VALUE) return;
		}
		memo[memoIdx++] = thisValue;
		dfs(idx, length + 1);
	}
	public static void init()  {
		for(int i=0; i<memo.length;i++) memo[i] = MAX_VALUE;
		memo[1] = 2;
		for(int i=3; i<1000000; i++) {
			dfs(i, 2);
		}
		Arrays.sort(memo);
	}
}
