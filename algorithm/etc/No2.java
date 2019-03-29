package algorithm;

public class No2 {

	public static void main(String[] args) {
		System.out.println(solution("23A84Q", "K2Q25J"));

	}
	public static int solution(String A, String B) {
		Deck deckOfAlec = new Deck(A);
		Deck deckOfBob = new Deck(B);
		int aN = A.length();
		int bN = B.length();
		if(aN!=bN) return -1;
		int res = 0;
		for(int i=0; i<aN; i++) {
			if(deckOfAlec.card[i]>deckOfBob.card[i])
				res++;
		}
		return res;
	}

}
class Deck {
	int[] card;
	Deck(String str) {
		String[] a = str.split("");
		card = new int[a.length+1];
		for(int i=0; i<a.length; i++) {
			if(a[i].equals("K")) {
				card[i] = 13;
			} else if(a[i].equals("Q")) {
				card[i] = 12;
			} else if(a[i].equals("J")) {
				card[i] = 11;
			} else if(a[i].equals("T")) {
				card[i] = 10;
			} else if(a[i].equals("A")) {
				card[i] = 14;
			} else {
				card[i] = Integer.parseInt(a[i]);
			}
		}
	}
}