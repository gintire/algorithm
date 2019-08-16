package letters;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/////////////////////////////////////////////////////////////////////////////////////////////
//기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
//아래 표준 입출력 예제 필요시 참고하세요.
//표준 입력 예제
//int a;
//double b;
//char g;
//String var;
//long AB;
//a = sc.nextInt();                           // int 변수 1개 입력받는 예제
//b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
//g = sc.nextByte();                          // char 변수 1개 입력받는 예제
//var = sc.next();                            // 문자열 1개 입력받는 예제
//AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
//표준 출력 예제
//int a = 0;                            
//double b = 1.0;               
//char g = &apos;b&apos;;
//String var = &quot;ABCDEFG&quot;;
//long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/


/**
 * 문제 풀이 전략
 * 1. 초기화 (입력값 받음)
 * - 배열에 시간 값을 넣어줌
 * - 전체 시간 TIME 값 전역
 * - 편지 배열 arrLetter
 * - 우편함 큐 queueLetterBox
 * - 결과 리스트 resultList
 * 2. 우편함 큐에 들어가면 시간 0
 * - 시간 흐를때 마다 우편함 큐에 들어가 있는 waittime 증가
 * 3. A개 이상의 편지가 모이거나, 가장 오래된 편지가 B이상일 때 편지함 비워주는 함수
 * - private static void rmLetter()
 * 4. 결과 리스트에 답 add
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
	/*
	아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
	여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
	이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
	따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
	단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
	*/
	//System.setIn(new FileInputStream(&quot;res/input.txt&quot;));
	
	/*
	표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
	*/
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int T = Integer.parseInt(br.readLine());
	/*
	여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
	*/
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String[] NAB = br.readLine().split(" ");
			int N = Integer.parseInt(NAB[0]);
			int A = Integer.parseInt(NAB[1]);
			int B = Integer.parseInt(NAB[2]);
		
			// 1. 초기화
			int[] arrLetter = new int[N];
			int time = 0;
			LinkedList<Integer> queueLetterBox = new LinkedList<Integer>();
			ArrayList<Integer> resultList = new ArrayList<Integer>();
			
			String[] temp = br.readLine().split(" ");
			for(int i=0; i<N; i++) { 
				arrLetter[i] = Integer.parseInt(temp[i]);
			}
			int idx = 0;
			int lastDelivery = arrLetter[N-1];
			while(true) {
				if(queueLetterBox.isEmpty() && time > lastDelivery) break;
				if(arrLetter[idx] == time) {
					queueLetterBox.offer(0);
					if(idx<N-1) idx++;
				}
				
				int letterBoxSize = queueLetterBox.size();
				if(!queueLetterBox.isEmpty()) {
					int rmLetterSize = (int) Math.ceil(letterBoxSize / 2.0 );
					if(letterBoxSize == A || queueLetterBox.peek() == B) {
						for(int i=0; i< rmLetterSize; i++) {
							queueLetterBox.poll();
							resultList.add(time);
						}
					}
				}
				
				runningWaitTime(queueLetterBox, queueLetterBox.size());
				
				time++;
			}
			System.out.print("#"+test_case+" ");
			for(int i=0; i<resultList.size(); i++) {
				System.out.print(resultList.get(i)+" ");
			}
			System.out.println();
		}
	}

	private static void runningWaitTime(LinkedList<Integer> queueLetterBox, int size) {
		/*for(int i=0; i<size; i++) {
			queueLetterBox.offer(queueLetterBox.poll()+1);
		}*/
		int idx = 0;
		for(int i : queueLetterBox)
			queueLetterBox.set(idx++, i+1);
	}
}