package org.movingpeople;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 2018 하반기 삼성전자 DS 
 * 백준저지 16234번 인구이동
 * https://www.acmicpc.net/problem/16234
 */

/**
 * 1. 두개의 숫자를 받아 비교하는 함수
 * 2. 위, 아래, 좌, 우 움직이는 함수
 * 
 */
public class MoveingPeople 
{
	static Integer[][] worldMap;
	static int N, L, R;
	static Integer[] x = {1, -1, 0, 0};
	static Integer[] y = {0, 0, 1, -1};
    public static void main( String[] args ) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] inputArr = input.split(" ");
        N = Integer.parseInt(inputArr[0]); L = Integer.parseInt(inputArr[1]); R = Integer.parseInt(inputArr[2]);
        worldMap = new Integer[N][N];
        
        for(int i=0; i<N ;i++) {
        	String[] peopleCnt = br.readLine().split(" ");
        	for(int j=0; j<N; j++) worldMap[i][j] = Integer.parseInt(peopleCnt[j]);
        }
        ArrayList<Integer> unionList = new ArrayList<Integer>();
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		for(int z=0; z<4; z++){
        			int diff = diffNumber(worldMap[i][j], worldMap[i+x[z]][j+y[z]]);
        			if(diff>= L || diff <= R) {
        				if(!unionList.equals(i*N+j))
        					unionList.add(i*N+j);
        			}
        		}
        	}
        }
        
    }
    
   public static void movingPeople(ArrayList<Integer> unionList, int[][] map, int n) {
	   int unionPeople = calUnionPeople(unionList, map, n);
	   for(int i=0; i<unionList.size(); i++) {
		   int point = unionList.get(i);
		   //worldMap[point/N][point%N] = unionPeople;
		   map[point/n][point%n] = unionPeople;
	   }
   } 
    
    // 연합 수
    public static int calUnionPeople(ArrayList<Integer> unionList, int[][] map, int n) {
    	int totPeople = 0;
    	int unionCnt = unionList.size();
    	for(int i=0; i<unionCnt;i++) {
    		int point = unionList.get(i);
    		//totPeople +=worldMap[point/N][point%N];
    		totPeople +=map[point/n][point%n];
    	}
    	return totPeople /unionCnt;
    }
    
    // 두 수의 차이
    public static int diffNumber(int a, int b) {
		return a-b>0?a-b:b-a;
    }
}