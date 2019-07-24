package oddNumberTower;

/**
 * SW academy 홀수층 피라미드 문제
 * 
 * 0번째 줄의 마지막 인덱스 : 0
 * 1번째 줄의 마지막 인덱스 : 0 + ((2*1) + 1)
 * 2번째 줄의 마지막 인덱스 : 0 + ((2*1) + 1) + ((2*2) + 1)
 * ...
 * n번째 줄의 마지막 인덱스 : 0 + ((2*1) + 1) + ((2*2) + 1) + ... +((2*n) + 1)
 * 
 * n번째 줄의 마지막 인덱스 : 2(1+2+...n) + n
 *                   2(n*(n+1)/2) + n
 *                   n*n + 2n
 * n번째 줄의 마지막 수 : 2 * n번째 마지막 인덱스 + 1
 * n번째 줄의 첫번째 수 : n번째 마지막 수 - 2(2n)
 * 
 * 
 */

import java.util.Scanner;

class Main
{
public static void main(String args[]) throws Exception
{
Scanner sc = new Scanner(System.in);
int T;
T=sc.nextInt();


for(int test_case = 1; test_case <= T; test_case++)
{
	long n = sc.nextLong();
	long lastIdx = 0;
	long firstNum = 1;
	long lastNum = 1;
	
	n= n-1;
	lastIdx = (n*n) + (2*n);
	lastNum = 2 * lastIdx + 1;
	firstNum = lastNum - 4*n;	
	
    System.out.println("#"+test_case+" "+ firstNum +" "+ lastNum +" ");
}
}
}