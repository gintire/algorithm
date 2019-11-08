package buyingNotebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] d;
	static int[] s;
	static int[] p;
	static int[] o;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			String[] NM = br.readLine().split(" ");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			
			d = new int[N + 1];
			s = new int[N + 1];
			p = new int[N + 1];
			o = new int[N + 1];
			for(int i=0; i < M;i++) {
				String[] SPO = br.readLine().split(" ");
				s[i] = Integer.parseInt(SPO[0]);
				p[i] = Integer.parseInt(SPO[1]);
				o[i] = Integer.parseInt(SPO[2]);
			}
			
			d[0] = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					
				}
			}
		}
	}

}
