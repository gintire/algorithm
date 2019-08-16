package subSummary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] numbers = new int[1000001];
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		SegmentTree sTree = new SegmentTree(N);
		
		sTree.init(sTree.tree, numbers, 1, 1, N);
		
		int a, b, c;
		
		for(int i=0; i< M + K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			// 수정
			if(a == 1) {
				long thisnum = numbers[b];
				long diff = c - thisnum;
				numbers[b] = c;
				
				sTree.update(sTree.tree, 1, 1, N, b, diff);
			}
			// 구간 합 출력
			else if(a == 2) {
				System.out.println(sTree.sum(sTree.tree, 1, 1, N, b, c));
			}
		}
	}
}
class SegmentTree{
	long tree[];
	int treeSize;
	
	public SegmentTree(int arrSize) {
		int tempK = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
		this.treeSize = (int) Math.pow(2, tempK) * 2;
		tree = new long[treeSize];
	}
	
	// init 함수
	protected static long init(long[] tree, int[] nums, int node, int start, int end)  {
		if(start == end) return tree[node] = nums[start];
		int mid = (start + end) / 2;
		return tree[node] = init(tree, nums, node * 2, start, mid) + init(tree, nums, node*2 +1, mid+1, end);
	}
	
	// update 함수
	protected static void update(long[] tree, int node, int start, int end, int idx, long diff) {
		if(!(start<=idx && idx<=end)) return;
		tree[node]+=diff;
		if(start != end) {
			int mid = (start + end) / 2;
			update(tree, node*2, start, mid, idx, diff);
			update(tree, node*2 + 1, mid + 1, end, idx, diff);
		}
	}
	
	// sum 함수
	protected static long sum (long[] tree, int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;
		if(left <= start && end <= right) return tree[node];
		int mid = (start + end) / 2;
		return sum(tree, node * 2, start, mid, left, right) + sum(tree, node*2 + 1, mid+1, end, left, right);
		
	}
}