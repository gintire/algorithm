package correctCalc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("C:\\Users\\jin36\\Documents\\test.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			long startTime = System.currentTimeMillis();
			int N = Integer.parseInt(br.readLine());
			String temp = br.readLine();
			StringTokenizer st = new StringTokenizer(temp);
			long[] prices = new long[N];
			
			long totalPrice = 0;
			int tokenIdx = 0;
			while(st.hasMoreTokens()) {
				long thisPrice = Long.parseLong(st.nextToken());
				//System.out.println("thisPrice : "+ thisPrice);
				prices[tokenIdx++] = thisPrice;
				totalPrice += thisPrice;
			}
			int count =0;
			//System.out.println("totalPrice : " + totalPrice);
			while(totalPrice!=0) {
				totalPrice /= 10;
				count++;
			}
			//System.out.println("count : "+ count);
			int[] countPerDec = new int[count];
			
			powerset(prices, N, countPerDec);
			
			int res = 0;
			for(int i=0; i<countPerDec.length;i++) {
				//System.out.println(countPerDec[i]);
				res+=countPerDec[i];
			}
			System.out.println("#"+test_case+" "+res);
			long stopTime = System.currentTimeMillis();
			System.out.println(stopTime - startTime);
		}
		br.close();
	}
	private static void powerset(long[] prices, int n, int[] countPerDec) {
		int pow = 1 << n;
		for(int i=1; i<pow; i++) {
			long totalPrices = 0;
			for(int j=0; j<n; j++) {
				if((i & (1 << j)) != 0) {
					totalPrices+=prices[j];
				}
			}
			correctCal(totalPrices, countPerDec);
		}
	}

	private static int[] correctCal(long totalPrices, int[] countPerDec) {
		int idx = 0;
		while(totalPrices != 0) {
			if(countPerDec[idx] < totalPrices % 10) countPerDec[idx] = (int) (totalPrices % 10);
			countPerDec[idx] = (int) Math.max(countPerDec[idx], totalPrices % 10);
			totalPrices /= 10;
			idx+=1;
		}
		return countPerDec;
	}
}
