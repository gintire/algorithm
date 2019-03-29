package algorithm;

import java.util.Stack;

public class No1 {

	public static void main(String[] args) {
		System.out.println(solution("13 DUP 4 POP 5 DUP + DUP + -"));
	}
	    public static int solution(String S) {
	       String[] a = S.split(" ");
	       Stack<Integer> st = new Stack<Integer>();
	       for(int i=0; i<a.length; i++) {
	           switch(a[i]) {
	               case "DUP" :
	                   st.push(st.peek());
	                   break;
	               case "POP" :
	                   st.pop();
	                   break;
	               case "+" :
	                   int first, second;
	                   if(!st.isEmpty()) {
	                       first = st.pop();
	                       if(!st.isEmpty()) {
	                           second = st.pop();
	                       } else return -1;
	                   } else return -1;
	                   st.push(first + second);
	                   break;
	               case "-" :
	                   if(!st.isEmpty()) {
	                       first = st.pop();
	                       if(!st.isEmpty()) {
	                           second = st.pop();
	                       } else return -1;
	                   } else return -1;
	                   if(first-second < 0) return -1;
	                   st.push(first - second);
	                   break;
	               default :
	                    st.push(Integer.parseInt(a[i]));
	                   break;
	           }
	       }
	       return st.pop();
	    }
}
