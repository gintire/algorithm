package tree_investment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MainTest {
/**
 * 해결 전략
 * 1. Map<Deque> 생성. 
 * 		Deque를 쓰는 이유 한칸에 여러 나무가 있을 때, 어린 나무 순서로 정렬하기 위해서
 * 2. 계절마다 요구사항 처리
 * 		봄 - 양분먹기, 나무죽이기 ( public void putEnergy(Deque))
 *        - 죽은 나무 List 생성 및 값 추가
 *      여름 - 죽은 나무 양분으로 주기 ( public void putMapEnergy(List) )
 *         - 다른 지역과 의존관계가 없으므로 이 행위는 봄에 같이 하면 될듯
 *      가을 - 나무 번식 ( public void postTrees() )
 *      겨울 - 양분 추가 (public void postMapEnergy() )
 */
	@Test
	public void springTest() {
		ArrayList<Integer> trees = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		List<Integer> deadTrees = new ArrayList<Integer>();
		int map = 5;
		Collections.sort(trees);
		for(int i=0; i < trees.size(); i++) {
			int now = trees.get(i);
			//양분이 충분할 경우
			if(now <= map) {
				map -= now;
				trees.set(i, now+1);
			} 
			// 양분이 부족할 경우
			else {
				deadTrees.add(i);
			}
		}
/*		System.out.print("deadTrees : ");
		for(int i=0; i<deadTrees.size(); i++) {
			System.out.print(deadTrees.get(i)+" ");
		}*/
		System.out.print("deadTrees : ");
		int initIndex = deadTrees.get(0);
		while(!deadTrees.isEmpty()) {
			System.out.print(deadTrees.get(0)+" ");
			int deadTreeIndex = deadTrees.get(0);
			map += trees.get(initIndex) / 2;
			trees.remove(initIndex);
			deadTrees.remove(0);
		}
		System.out.println();
		System.out.print("Trees : ");
		for(int i=0; i<trees.size(); i++) {
			System.out.print(trees.get(i)+" ");
		}
		assertEquals(7, map);	
	}
}
