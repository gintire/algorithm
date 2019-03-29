package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Reservation {
	private Map<Character, Integer> blockTable = new HashMap<Character, Integer>();
	private Map<Character, Integer> seatTable = new HashMap<Character, Integer>();
	private List<boolean[][]> seats;
	private final int FAMILTY_NUMBER = 3;
	private final char[][] blockInfoArr = new char[][]{{'A','C'},{'D','G'},{'H','K'}};
	
	private void generateBlockSeat(char start, char end, int index) {
		int value = 0;
		for (char c = start; c <= end; c++) {
			blockTable.put(c, index);
			seatTable.put(c, value++);
		}
	}
	Reservation(int N) {
		for (int i = 0; i < blockInfoArr.length; i++) generateBlockSeat(blockInfoArr[i][0],blockInfoArr[i][1],i);

		seats = new ArrayList<boolean[][]>();
		seats.add(new boolean[N][3]);
		seats.add(new boolean[N][4]);
		seats.add(new boolean[N][3]);
	}
	
	public void reserveSeat(String S) {
		if ("".equals(S)) return;			// empty							
		String[] reservedSeats = S.split(" ");
		for (String s : reservedSeats) {
			boolean[][] c = seats.get(blockTable.get(s.charAt(s.length()-1)));
			int row = Integer.parseInt(s.substring(0, s.length()-1)) - 1;
			int col = seatTable.get(s.charAt(s.length()-1));
			c[row][col] = true;
		}
	}
	/**
	 * 
	 * @author : hoTire
	 * @comment : 시간복잡도 N
	 */
	public int getAvaliableSeatsNumber(int n) {
		
		int ans = 0;
		for (boolean[][] c : seats) {					// 상수 	
			for (int i = 0; i < c.length; i++) {		// N
				int count = 0;
				for (int j = 0; j < c[i].length; j++) { // 상수
					if (count >= FAMILTY_NUMBER) break;
					count = c[i][j] ? 0 : count + 1;
				}
				ans = count >= FAMILTY_NUMBER ? ans + 1 : ans;
			}
		}
		return ans;
	}
	
}

public class EstSoftNo2 {
	
	public static void main(String args[]) {
//		int N = 2;
//		String S = "1A 2F 1C";
		int N = 1;
		String S ="";
		int ans = new EstSoftNo2().solution(N, S);
		System.out.println(ans);
	}
	
	/**
	 * 
	 * @author : hoTire
	 * 
	 * A B C	D E F G		H J K  = 10
	 * N = 50  
	 * 2 x 2 = 500 
	 * 
	 * 
	 */
	public int solution(int N, String S) {
		Reservation reservation = new Reservation(N);
		reservation.reserveSeat(S);	
		return reservation.getAvaliableSeatsNumber(3);
	}
}