적록색약
===============
> 백준 10026번 문제
> https://www.acmicpc.net/problem/10026

<hr/>
> * 풀이 전략
> * 1. 초기화 ( N과 N*N 그리드를 받는다)
> * 2. 맵을 스캔하는 함수 생성 (scan())
> *    방문한적이 없는 곳이면 컬러 그룹에 넣는다. (arraylist cGroup, rgcGroup)
> *    cGroup - 일반인, rgcGroup - 적록색약인
> * 3. 연속된 같은 색을 찾는다. (dfs)
> *    방문한 곳은 방문한것 표시 (visited)
> *    색약일 경우 (R, G를 같은 색상으로 취급)
> * 4. 색 비교 함수 생성 ( 색약일 경우와, 색약이 아닐 경우 boolean isSameColor(char a, char b))
> * 5. 전역 변수 N
> *    char[][] map
> *    boolean[][] visited

> 테스트 코드 - Utils - CreateRGBMap.java 참조
