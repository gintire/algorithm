import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
class Number3 {
    public static ArrayList<String> answers = new ArrayList<String>();
    public static String[][] table;
    public static int solution(String[][] relation) {
        int r = relation.length;
        int c = relation[0].length;
        List<Integer> list = new LinkedList<Integer>();
        table = relation;
        for(int i=1; i<1<<c; i++) {
        	if(isCandidate(r, c, i)) {
        		list.add(i);
        	}
        }
        list.stream().sorted((x,y)-> {
        	int prix = prio(x);
            int priy = prio(y);

            if(prix > priy) {
                return 1;

            }else if(priy < priy) {

                return -1;
            }else {

                return 0;
            }
        });
	    int ans = 0;
	    while(!list.isEmpty()) {
	    
        int first = list.remove(0);
        ans ++;

        list.removeIf(i->(first & i)==first);
    } // 유일성을 위해서 조합이 만들어질경우 제거한다.      
    return ans;
    }
    private static int prio (int x) {
        int ret = 0;
            while(x != 0) {
                if((x & 1) == 1) {
                    ret++;
                    x = x >> 1;
                }
            }
        return ret;
    };
    private static boolean isCandidate(int r, int c, int comb) {
    	for(int i = 0; i < r-1 ; i++) {
            for(int j = i+1; j < r ; j++) {
                    boolean isSame = true;

                for(int k = 0; k < c ;k ++) {

                    if((comb & 1 << k) == 0) continue;
                    if(!table[i][k].equals(table[j][k])) {
                        isSame = false;
                        break;
                    }
                }
                
                if(isSame) return false; // 같으므로 후보키 아님
            }
        }
        return true;
	}
}