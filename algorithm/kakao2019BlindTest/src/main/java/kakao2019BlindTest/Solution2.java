package kakao2019BlindTest;

public class Solution2 {

	public static String solution(String p) {
        String answer = "";
        answer = recursive(p);        
        return answer;
    }
	
	private static String recursive(String str) {
		if(str =="") return "";
		int open = 0;
    	int close = 0;
    	int uvIdx = 0;
    	for(int i=0; i<str.length(); i++) {
        	if(str.substring(i, i+1).equals("(")) {
        		open++;
        	}
        	else {
        		close++;
        	}
			if(open == close) {
				uvIdx = i;
				break;
			}
        }
    	String u = "";
        String v = "";
    	
    	for(int i=0; i<=uvIdx; i++) {
    		u+=str.substring(i, i+1);
    	}
    	for (int i=uvIdx+1; i<str.length();i++) {
    		v+=str.substring(i, i+1);
    	}
    	
    	if(checkPerfectP(u)) u+=recursive(v);
    	else u = "(" + recursive(v) +")"+reverse(u.substring(1, u.length()-1));
		return u;
	}
	
	private static String reverse(String str) {
		String tempStr = "";
		for(int i=0; i<str.length();i++) {
			if(str.substring(i, i+1).equals("(")) tempStr+=")";
			else if(str.substring(i, i+1).equals(")")) tempStr+="(";
		}
		return tempStr;
	}

	private static boolean checkPerfectP(String str) {
		if(checkValiable(str)) return true;
		return false;
	}
	
	// 올바른 괄호 문자열 검색
	private static boolean checkValiable(String str) {
		if(checkBalanced(str)) {
			int open = 0;
	    	int close = 0;
	    	for(int i=0; i<str.length(); i++) {
	        	if(str.substring(i, i+1).equals("(")) {
	        		open++;
	        	}
	        	else {
	        		close++;
	        	}
	        	if(close > open) return false;
	        }	
		} else {
			return false;
		}
		return true;
	}
	
	// 균형잡힌 관호 문자열 검색
	private static boolean checkBalanced(String str) {
		int open = 0;
    	int close = 0;
    	for(int i=0; i<str.length(); i++) {
        	if(str.substring(i, i+1).equals("(")) {
        		open++;
        	}
        	else {
        		close++;
        	}
        }
    	if(open == close) return true; 
		return false;
	}
}
