package naver2019;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

import org.junit.Test;

public class Solution1 {

	@Test
	public void test_cast() {
		String[] input1 = {
				"RECEIVE abcd@naver.com", 
				"RECEIVE zzkn@naver.com", 
				"DELETE", 
				"RECEIVE qwerty@naver.com",
				"SAVE",
				"RECEIVE QwerTY@naver.com"
		};
		String[] expected= {"abcd@naver.com", "qwerty@naver.com"};
		assertArrayEquals(solution(input1), expected);
	}
	
	
	public static String[] solution (String[] record) {
		//String[] answer = {};
		ArrayList<String> answer = new ArrayList<String>();
		Deque<String> cache = new LinkedList<>();
		IntStream.range(0, record.length).forEach(i->{
			String[] temp = record[i].split(" ");
			if(temp[0].equals("RECEIVE")) {
				cache.offer(temp[1]);
			}else if(temp[0].equals("DELETE")) {
				cache.pollLast();
			}else if(temp[0].equals("SAVE")) {
				while(!cache.isEmpty()) {
					answer.add(cache.pollFirst());
				}
			}
		});
		return answer.toArray(new String[answer.size()]);
	}
}
