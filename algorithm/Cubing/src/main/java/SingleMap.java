import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SingleMap {
	static String[] U = new String[9];
	static String[] D = new String[9];
	static String[] F = new String[9];
	static String[] B = new String[9];
	static String[] L = new String[9];
	static String[] R = new String[9];
	
	public static void initCube() {
		for (int i=0; i<9; i++) {
			U[i]="w";D[i]="y";F[i]="r";
			B[i]="o";L[i]="g";R[i]="b";
		}
	}
	
	//U : ��(w), D : ��(y), F : ��(r), B : ������(o), L : �ʷ�(g), R : �Ķ� (b)
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i=0 ; i<cases; i++) {
			String input = br.readLine();
			solve(input);
		}
	}

	private static void solve(String input) throws IOException {
		initCube();
		String oriStr = input;
		String[] str = oriStr.split(" ");
		for(int i=0; i<str.length; i++) {
			String[] str1 = str[i].split("");
			for(int j=0; j<str1.length; j+=2) {
				if(str1[j].equals("U")) {
					if(str1[j+1].equals("+")) turnU(true);
					if(str1[j+1].equals("-")) turnU(false);
				}
				if(str1[j].equals("D")) {
					if(str1[j+1].equals("+")) turnD(true);
					if(str1[j+1].equals("-")) turnD(false);
				}
				if(str1[j].equals("F")) {
					if(str1[j+1].equals("+")) turnF(true);
					if(str1[j+1].equals("-")) turnF(false);
				}
				if(str1[j].equals("B")) {
					if(str1[j+1].equals("+")) turnB(true);
					if(str1[j+1].equals("-")) turnB(false);
				}
				if(str1[j].equals("L")) {
					
					if(str1[j+1].equals("+")) turnL(true);
					if(str1[j+1].equals("-")) turnL(false);
				}
				if(str1[j].equals("R")) {
					if(str1[j+1].equals("+")) turnR(true);
					if(str1[j+1].equals("-")) turnR(false);
				}
			}
			
		}
//			StringBuilder sb = new StringBuilder("");
			for (int a=0; a<9; a++) {
				if(a==3 || a==6)System.out.println();
				System.out.print(U[a]);
				
				//sb.append(U[a]);
			}
			System.out.println();
			//System.out.println(sb.toString());
			
	}

	private static void turnR(boolean clockway) {
		String[] temp = new String[3];
		temp[0] = U[2]; temp[1] = U[5]; temp[2] = U[8];		
		//�ð����
		if(clockway) {
			U[2] = F[2]; U[5] = F[5]; U[8] = F[8];
			F[2] = D[2]; F[5] = D[5]; F[8] = D[8];
			D[2] = B[6]; D[5] = B[3]; D[8] = B[0];
			B[6] = temp[0]; B[3] = temp[1]; B[0] = temp[2];
		} else {
			U[2] = B[6]; U[5] = B[3]; U[8] = B[0];
			B[6] = D[2]; B[3] = D[5]; B[0] = D[8];
			D[2] = F[2]; D[5] = F[5]; D[8] = F[8];
			F[2] = temp[0]; F[5] = temp[1]; F[8] = temp[2];
		}
		
		// ��ü ȸ��
		turnPlate(5, clockway);
	}

	private static void turnL(boolean clockway) {
		String[] temp = new String[3];
		temp[0] = U[0]; temp[1] = U[3]; temp[2] = U[6];		
		//�ð����
		if(clockway) {
			U[0] = B[8]; U[3] = B[5]; U[6] = B[2];
			B[8] = D[0]; B[5] = D[3]; B[2] = D[6];
			D[0] = F[0]; D[3] = F[3]; D[6] = F[6];
			F[0] = temp[0]; F[3] = temp[1]; F[6] = temp[2];
		} else {
			U[0] = F[0]; U[3] = F[3]; U[6] = F[6];
			F[0] = D[0]; F[3] = D[3]; F[6] = D[6];
			D[0] = B[8]; D[3] = B[5]; D[6] = B[2];
			B[8] = temp[0]; B[5] = temp[1]; B[2] = temp[2];
		}
		
		// ��ü ȸ��
		turnPlate(4, clockway);	
	}

	private static void turnB(boolean clockway) {
		String[] temp = new String[3];
		temp[0] = U[0]; temp[1] = U[1]; temp[2] = U[2];
		//�ð����
		if(clockway) {
			U[0] = R[2]; U[1] = R[5]; U[2] = R[8];
			R[2] = D[8]; R[5] = D[7]; R[8] = D[6];
			D[8] = L[6]; D[7] = L[3]; D[6] = L[0];
			L[6] = temp[0]; L[3] = temp[1]; L[0] = temp[2];
		} else { 
			U[0] = L[6]; U[1] = L[3]; U[2] = L[0];
			L[6] = D[8]; L[3] = D[7]; L[0] = D[6];
			D[8] = R[2]; D[7] = R[5]; D[6] = R[8];
			R[2] = temp[0]; R[5] = temp[1]; R[8] = temp[2];
		}
		
		// ��ü ȸ��
		turnPlate(3, clockway);
	}

	private static void turnF(boolean clockway) {
		String[] temp = new String[3];
		temp[0] = U[6]; temp[1] = U[7]; temp[2] = U[8];
		//�ð����
		if(clockway) {
			U[8] = L[2]; U[7] = L[5]; U[6] = L[8];
			L[2] = D[0]; L[5] = D[1]; L[8] = D[2];
			D[0] = R[6]; D[1] = R[3]; D[2] = R[0];
			R[6] = temp[2]; R[3] = temp[1]; R[0] = temp[0];
		} else {
			U[8] = R[6]; U[7] = R[3]; U[6] = R[0];
			R[6] = D[0]; R[3] = D[1]; R[0] = D[2];
			D[0] = L[2]; D[1] = L[5]; D[2] = L[8];
			L[2] = temp[2]; L[5] = temp[1]; L[8] = temp[0];
		}
		
		// ��ü ȸ��
		turnPlate(2, clockway);
	}

	private static void turnD(boolean clockway) {
		String[] temp = new String[3];
		temp[0] = F[6]; temp[1] = F[7]; temp[2] = F[8];
		//�ð����
		if(clockway) {
			F[6] = L[6]; F[7] = L[7]; F[8] = L[8];
			L[6] = B[6]; L[7] = B[7]; L[8] = B[8];
			B[6] = R[6]; B[7] = R[7]; B[8] = R[8];
			R[6] = temp[0]; R[7] = temp[1]; R[8] = temp[2];
		} else {
			F[6] = R[6]; F[7] = R[7]; F[8] = R[8];
			R[6] = B[6]; R[7] = B[7]; R[8] = B[8];
			B[6] = L[6]; B[7] = L[7]; B[8] = L[8];
			L[6] = temp[0]; L[7] = temp[1]; L[8] = temp[2];
			
		}
		
		// ��ü ȸ��
		turnPlate(1, clockway);
	}

	private static void turnU(boolean clockway) {
		String[] temp = new String[3];
		temp[0] = F[0]; temp[1] = F[1]; temp[2] = F[2];
		//�ð����
		if(clockway) {
			F[0] = R[0]; F[1] = R[1]; F[2] = R[2];
			R[0] = B[0]; R[1] = B[1]; R[2] = B[2];
			B[0] = L[0]; B[1] = L[1]; B[2] = L[2];
			L[0] = temp[0]; L[1] = temp[1]; L[2] = temp[2];
		} else {
			F[0] = L[0]; F[1] = L[1]; F[2] = L[2];
			L[0] = B[0]; L[1] = B[1]; L[2] = B[2];
			B[0] = R[0]; B[1] = R[1]; B[2] = R[2];
			R[0] = temp[0]; R[1] = temp[1]; R[2] = temp[2];
		}
		
		// ��ü ȸ��
		turnPlate(0, clockway);
	}		
	
	//��ǥ���, 
	private static void turnPlate(int idx, boolean clockway) {
		Map<Integer, String[]> cube = new HashMap<Integer, String[]>();
		cube.put(0, U);cube.put(1, D);cube.put(2, F);
		cube.put(3, B);cube.put(4, L);cube.put(5, R);
		String[] temp = new String[9];
		for(int i=0; i<9; i++) 
			temp[i] = cube.get(idx)[i];
		// �ð� ���� ȸ��
		if(clockway) {
			cube.get(idx)[2] = temp[0]; cube.get(idx)[0] = temp[6]; cube.get(idx)[3] = temp[7];
			cube.get(idx)[5] = temp[1]; cube.get(idx)[1] = temp[3]; cube.get(idx)[4] = temp[4];
			cube.get(idx)[8] = temp[2]; cube.get(idx)[7] = temp[5]; cube.get(idx)[6] = temp[8];
		} else {
			cube.get(idx)[6] = temp[0]; cube.get(idx)[4] = temp[4]; cube.get(idx)[8] = temp[6];
			cube.get(idx)[3] = temp[1]; cube.get(idx)[1] = temp[5]; cube.get(idx)[7] = temp[3];
			cube.get(idx)[0] = temp[2]; cube.get(idx)[2] = temp[8]; cube.get(idx)[5] = temp[7];
		}
		
		
	}
}
