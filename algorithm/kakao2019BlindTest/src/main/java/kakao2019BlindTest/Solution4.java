package kakao2019BlindTest;

import java.util.ArrayList;
import java.util.Collections;

public class Solution4 {
    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        ArrayList<String> arr = new ArrayList<String>();
        for(int i=0; i<words.length;i++) {
        	arr.add(words[i]);
        }
        Collections.sort(arr);
        
        for(int i=0; i<queries.length; i++) {
        	int count = 0;
        	String thisStr = queries[i];
        	String FB = "F";
        	int idx = 0;
        	for(int j=0; j<thisStr.length(); j++) {
        		if(j==0) {
        			if(thisStr.substring(j, j+1).equals("?")) {
        				FB = "F";
        			} else 
        				FB = "B";
        		}
        		if(FB.equals("F")) {
        			if(!thisStr.substring(j, j+1).equals("?")) {
        				idx = j;
        				break;
        			}
        		}
        		else {
        			if(thisStr.substring(j, j+1).equals("?")){
        				idx = j;
        				break;
        			}
        		}
        	}
        	
        	for(int j=0; j<arr.size(); j++) {
        		if(arr.get(j).length() != thisStr.length()) continue;
        		if(FB.equals("F")){
        			String word = words[j].substring(idx, words[j].length());
        			String queri = thisStr.substring(idx, thisStr.length());
        			if(word.equals(queri)) {
        				count++;
        			}
        		}else {
        			if(arr.get(j).substring(0, idx).compareTo(thisStr.substring(0, idx)) > 0) break;
        			if(arr.get(j).substring(0, idx).equals(thisStr.substring(0, idx))) {
        				count++;
        			}
        		}
        	}
        	answer[i]=count;
        }
        return answer;
    }
}
