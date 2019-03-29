package algorithm;

public class FlogJmp {
	public static void main(String[] args) {
		System.out.println(solution(10, 85, 30));
	}
	public static int solution(int X, int Y, int D) {
		if((Y-X)%D > 0)
			return (Y-X)/D+1;
		else
			return (Y-X)/D;
    }
}
