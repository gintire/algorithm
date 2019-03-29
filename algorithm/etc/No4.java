package algorithm;


public class No4 {
	public static void main(String[] args) {
		System.out.println(solution(2,4,2,4));

	}
	public static int solution(int A, int B, int C, int D) {
		//AB, CD
		int res = 0;
		int[] arr = new int[4];
		int[] ABCD = {A, B, C, D};
		for(int i=0; i<4; i++) {
			arr[i] = i+1;
		}
		int a = 0, b = 0, c = 0 , d = 0;
		int maxDist = 0;
		int tmpDist = 0;
		do {
			for(int i=0; i<arr.length; i++) {
				System.out.print(arr[i] + " ");
				if(arr[i]==1) a = ABCD[i];
				if(arr[i]==2) b = ABCD[i];
				if(arr[i]==3) c = ABCD[i];
				if(arr[i]==4) d = ABCD[i];
			}
			System.out.println();
			System.out.println(a+" "+b+" "+c+" "+d);
			Point p1 = new Point(a, b);
			Point p2 = new Point(c, d);
			
			tmpDist = p1.getSquaredDistance(p2);
			System.out.println("tmp : "+tmpDist);
			if(maxDist < tmpDist)
				maxDist = tmpDist;
			System.out.println();
		}while(nextPermutation(arr));
		return maxDist;
    }
	 static boolean nextPermutation(int[] arr) {
			int n = arr.length;
	    	int i = n - 1;
			int j = n - 1;
			int tmp = 0;
			while(i>0&&arr[i]<=arr[i-1]) i--;
			if(i<=0) return false;
			while(arr[j]<=arr[i-1])j--;
			tmp = arr[i-1];
			arr[i-1] = arr[j];
			arr[j] = tmp;

			while ( j > i ) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
			return true;
		}
}
class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getSquaredDistance(Point p) {
		return (this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y);
	}
}
