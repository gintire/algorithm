package kakao2019BlindTest;

public class Solution1 {
	public static int answer = Integer.MAX_VALUE;
	public static String ansStr;
   public static int solution(String s) {
        int length = s.length();
        
        System.out.println(length);
        for(int i=1; i<=length/2; i++) {
        	ansStr = "";
        	getLength(s, i, 0, 1);
        	System.out.println("N : "+ i+" "+ ansStr);
        	answer = Math.min(answer, ansStr.length());
        }
        return answer;
   }
   // idx, 문자 비교할 때 String s에서의 idx
   // n, n개로 나눠서 비교
   // 만약 n개로 나눠서 비교할 때 반복되지 않는다면 중지

   private static void getLength(String s, int n, int idx, int count) {
	  int sLength = s.length();
	  if(idx + n + n <= sLength) {
		  System.out.println(s.substring(idx, idx+n)+ "  "+s.substring(idx+n, idx+n+n));
		  if(s.substring(idx, idx+n).equals(s.substring(idx+n, idx+n+n))) {
			  getLength(s, n, idx+n, count+1);
		  } else {
			  if(count == 1) {
				  ansStr = ansStr.concat(s.substring(idx, idx + n));
				  getLength(s, n, idx+n, 1);
			  } else {
				  ansStr = ansStr.concat(count + s.substring(idx, idx + n));
				  getLength(s, n, idx+n, 1);
			  }
		  }
	  } else {
		  if(count == 1) {
			  ansStr = ansStr.concat(s.substring(idx, idx+n));
		  } else {
			  ansStr = ansStr.concat(count + s.substring(idx, idx+n));
		  }
		  
		  if(idx +n != sLength) {
			  ansStr = ansStr.concat(s.substring(idx+n, sLength));
		  }
	  }
   }
}
