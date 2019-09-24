package naver2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution3 {

  public static void main(String[] args) {
//    int[] coo_times = new int[]{5,30,15,30,35,20,4};
//    int[][] order = new int[][]{{2,4}, {2,5}, {3,4}, {3,5}, {1,6}, {4,6}, {5,6}, {6,7}};

    int[] cook_times = new int[]{5,30,15,30,35,20,4,50,40};
    int[][] order = new int[][]{{2,4}, {2,5}, {3,4}, {3,5}, {1,6}, {4,6}, {5,6}, {6,7}, {8,9}};

//        int[] coo_times = new int[]{5,3,2};
//    int[][] order = new int[][]{{1,2}, {2,3}, {1,3}};

    int[] ans = solution(cook_times, order, 9);
    System.out.println(Arrays.toString(ans));
  }

  private static int[] COOK_TIMES;

  public static int[] solution(int[] cook_times, int[][] order, int k) {
    final Map<Integer, Set<Integer>> orderMap = new HashMap<>();
    COOK_TIMES = cook_times;
    Arrays.stream(order).forEach(arr -> {
      int pre = arr[0];
      int cur = arr[1];

      if (orderMap.containsKey(cur)) {
        orderMap.get(cur).add(pre);
      } else {
        Set<Integer> set = new HashSet<>();
        set.add(pre);
        orderMap.put(cur, set);
      }
    });

    final Map<Integer, Integer> ans = new HashMap<>();
    orderMap.get(k).forEach(integer -> dfs(integer, orderMap, ans));

    int count = ans.size();
    int sum = ans.get(k-1) + COOK_TIMES[k-1];

    return new int[]{count, sum};
  }

  public static void dfs(Integer target, Map<Integer, Set<Integer>> orderMap, Map<Integer, Integer> ans) {
	    if (ans.containsKey(target)) {
	      return;
	    }

	    if (orderMap.containsKey(target)) {
	      Set<Integer> set = orderMap.get(target);
	      set.forEach(integer -> dfs(integer, orderMap, ans));
	      int sum = set.stream().mapToInt(index -> COOK_TIMES[index-1]).max().orElse(0);
	      sum += COOK_TIMES[target-1];
	      ans.put(target, sum);
	      return;
	    }
	    ans.put(target, COOK_TIMES[target-1]);
	  }
}