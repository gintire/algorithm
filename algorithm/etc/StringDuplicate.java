package algorithm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;

import org.junit.Test;

public class StringDuplicate {
	@Test
	public void main() {
		assertEquals(solution("ACCAABBC"), "AC");
		//assertEquals(solution("ABCBBCBA"), "AA");
		//assertEquals(solution("BABABA"), "BABABA");
	}
	
	private static String solution(String str) {
		StringBuilder res = new StringBuilder();
	
		for(int i=1; i < str.length(); i++) {
			String str1 = String.valueOf(str.charAt(i-1));
			String str2 = String.valueOf(str.charAt(i));

			//System.out.println(str1 + " " +str2 + " "+str1.equals(str2));
			System.out.println(i);
			if(str1.equals(str2)) {
				i+=1;
			} else {
				res.append(str1);
				System.out.println(i+" "+str.length());
				if(i==str.length())
					res.append(str2);

			}
		}
		return res.toString();
	}
}
