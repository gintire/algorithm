package chicken_delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] gsb1 = br.readLine().split(" ");
		Money m1 = new Money(Integer.parseInt(gsb1[0]), Integer.parseInt(gsb1[1]), Integer.parseInt(gsb1[2]));
		String[] gsb2 = br.readLine().split(" ");
		Money m2 = new Money(Integer.parseInt(gsb2[0]), Integer.parseInt(gsb2[1]), Integer.parseInt(gsb2[2]));
		
		
		int count = 0;
		while(m1.compare(m2)) {
		    // G1이 G2보다 작을 경우
			if(m1.getG() < m2.getG()) {
				// 부족한 금화
				int diffG = m2.getG() - m1.getG();
				int restSilver = m1.getS() - m2.getS();
				int restBronze = m1.getB() - m2.getB();
				//  금화를 바꿀만큼 은화와 동화가 없을 경우 끝
				/*if(restSilver > 0 && restBronze > 0) {
					if((restSilver / 11) + (restBronze / (11*11)) < diffG) {
						count = -1;
						break;
					}
				} else if (restSilver > 0 && restBronze <= 0) {
					if(restSilver / 11 < diffG) {
						count = -1;
						break;
					}
				} else if (restSilver <= 0 && restBronze > 0) {
					if(restBronze / (11*11) < diffG) {
						count = -1;
						break;
					}
				} else if(restSilver <= 0 && restBronze <= 0){
					count = -1;
					break;
				}*/
				// 은화 먼저 환전
				if(restSilver > 10) {
					m1.setS(m1.getS() - 11);
					m1.setG(m1.getG() + 1);
					count += 1;
				}
				//은화가 부족하면 동화를 환전
				else if(restBronze > 10){
					m1.setB(m1.getB() - 11);
					m1.setS(m1.getS() + 1);
					count+= 1;
				} else {
					count = -1;
					break;
				}
			} 
			// 금화는 조건에 충족
			else {
				// S1이 S2보다 작을 경우
				if(m1.getS() < m2.getS()) {
					// 잉여 금화
					int restGold = m1.getG() - m2.getG();
					int restBronze = m1.getB() - m2.getB();
					// 부족한 은화
					int diffS = m2.getS() - m1.getS();
					// 금화 또는 동화에서 땡겨 쓰려면
					// 잉여 금화, 동화가 있어야 한다.
					/*if(restGold > 0 && restBronze > 0) {
						if((restGold * 9) + (restBronze / 11) < diffS) {
							count = -1;
							break;
						}
					} else if (restGold > 0 && restBronze <= 0) {
						if(restGold * 9 < diffS) {
							count = -1;
							break;
						}
					} else if (restGold <= 0 && restBronze > 0) {
						if(restBronze / 11 < diffS) {
							count = -1;
							break;
						}
					} else if(restGold <= 0 && restBronze <= 0){
						count = -1;
						break;
					}*/
					// 잉여 금화가 있을 경우
					if(restGold > 0) {
						m1.setG(m1.getG() - 1);
						m1.setS(m1.getS() + 9);
						count +=1;
					} else if(restBronze > 10){
						m1.setB(m1.getB() - 11);
						m1.setS(m1.getS() + 1);
						count += 1;
					} else {
						count = -1;
						break;
					}
				} 
				// 금화, 은화 조건이 충족
				else {
					// B1이 B2보다 작을 경우	
					if(m1.getB() < m2.getB()) {
						// 잉여 금, 은화
						int restGold = m1.getG() - m2.getG();
						int restSilver = m1.getS() - m2.getS();
						// 부족한 동화
						int diffB = m2.getB() - m1.getB();
						// 잉여 금, 은화가 없을 경우 끝
						// 금, 은화를 환전해도 동화가 없을 경우
						/*if(restGold > 0 && restSilver > 0) {
							if((restGold * 9 * 9) + (restSilver * 9) < diffB) {
								count = -1;
								break;
							}
						} else if (restGold > 0 && restSilver <= 0) {
							if(restGold * 9 * 9 < diffB) {
								count = -1;
								break;
							}
						} else if (restGold <= 0 && restSilver > 0) {
							if(restSilver * 9 < diffB) {
								count = -1;
								break;
							}
						} else if(restGold <= 0 && restSilver <= 0){
							count = -1;
							break;
						} */
						// 부족한 동화
						// 은화가 부족하면 금화를 땡겨 써야함
					    // 금화에서 땡겨 쓰려면 잉여 금화가 있어야한다.
						// 은화에서 땡겨 쓰려면 잉여 은화가 있어야 한다.
						if(restSilver > 0) {
							m1.setS(m1.getS() - 1);
							m1.setB(m1.getB() + 9);
							count+=1;
						} else if(restGold > 0){
							m1.setG(m1.getG() - 1);
							m1.setS(m1.getS() + 9);
							count +=1;								
						} else {
							count = -1;
							break;
						}
					}
				}
			}
		}
		System.out.println(count);
	}

}
class Money{
	int g, s, b;

	public Money(int g, int s, int b) {
		this.g = g;
		this.s = s;
		this.b = b;
	}
	public boolean compare(Money other) {
		if(this.g >= other.g && this.s >= other.s && this.b >= other.b) return false;
		return true;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
}
