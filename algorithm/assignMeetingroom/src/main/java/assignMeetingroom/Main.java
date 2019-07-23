package assignMeetingroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 백준 1931번
 * 회의실 배정
 * 0. 단위 미팅 클래스 생성 ( Meeting )
 * 1. 입력값 받기 (초기화)
 * 2. 시간 비교 함수 (boolean compareTime (Meeting a, Meeting b) )
 *    - 끝나는 동시에 회의 시작 가능
 *    - 회의 시작시간 == 끝나는 시간 일 경우도 있다.
 * 3. 정렬
 *    - 끝나는 시간 별로 먼저 정렬하고, 시작 시간별로 정렬한다.
 *    - 그 이유는 끝난 시간 바로 다음에 시작 시간이 빠른게 오는게 이득
 *    - 그리고 끝난 시간으로 정렬이 되기 때문에 단위 회의 시간이 긴 것은 신경쓸 필요가없다.
 *    - 어차피 회의 시간이 길다 = 끝나는 시간이 늦다 = 배열의 뒷부분으로 간다. = 최적이 될 수 없다.
 *    - 시작 시간 별로 정렬하기 때문에 먼저 시작하는애가 먼저 정렬된다.
 * 4. 그리디 알고리즘으로 해결
 *    - 정렬이 끝났기 때문에 끝난 시간 다음에 오는 가장 가까운 시작시간 회의를 배정하면 된다.
 *    - 시간 복잡도는 
 *       정렬 ( java.util.Arrays.sort() 알고리즘은 merge sort 사용) = nlogn
 *       정렬 된 배열에서 회의실 배정 상수
 *       O(nlogN) 이 된다.
 * 제약 사항
 * jdk 1.8 이상 사용
 * */

public class Main {
	static ArrayList<Meeting> arrayMeetings;
	static int maxMeeting = 0;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		// 1. 초기화
		// meetings ( 시작시간, 종료시간 )
		arrayMeetings = new ArrayList<Meeting>();
		String[] tmpString;
		for(int i=0; i<n; i++) {
			tmpString = br.readLine().split(" ");
			arrayMeetings.add(new Meeting(Integer.parseInt(tmpString[0]), Integer.parseInt(tmpString[1])));
		}
		// 3. 정렬
		arrayMeetings.sort((o1, o2)->o1.compareTo(o2));
		// 4. greedy Algorithm
		getMaxAssignMeetingsCount(0, 1, 1);
		System.out.println(maxMeeting);
	}
	
	//4. dfs를 사용해서 문제 해결 
	public static void getMaxAssignMeetingsCount (int a, int b, int count) {
		if(b == n) {
			if(count >= maxMeeting) maxMeeting = count;
		}
		if(a >= n || b >= n) return;
		if(compareTime(arrayMeetings.get(a), arrayMeetings.get(b))) {
			getMaxAssignMeetingsCount (b, b+1, count + 1);
		} else {
			getMaxAssignMeetingsCount (a, b+1, count);
		}
	}
	//2. 시간 비교 함수
	public static boolean compareTime(Meeting a, Meeting b) {
		if(b.startTime - a.endTime >=0 ) return true; 
		return false;
	}
}

// 0. 단위 미팅
class Meeting implements Comparable<Meeting>{
	int startTime;
	int endTime;
	
	public Meeting(int startTime, int endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int compareTo(Meeting o) {
		if(this.endTime == o.endTime) {
			return this.startTime - o.startTime;
		}
		return this.endTime - o.endTime;
	}
	
}