import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static String [][][] cube = new String[6][3][3];
	
	public static void initCube() {
		for (int i=0; i<6; i++) {
			for(int j=0; j<3; j++) {
				for (int k=0; k<3; k++) {
					if(i==0) cube[i][j][k] = "w";
					if(i==1) cube[i][j][k] = "y";
					if(i==2) cube[i][j][k] = "r";
					if(i==3) cube[i][j][k] = "o";
					if(i==4) cube[i][j][k] = "g";
					if(i==5) cube[i][j][k] = "b";
				}
			}
		}
	}
	
	//U : 흰(w), D : 노(y), F : 빨(r), B : 오렌지(o), L : 초록(g), R : 파랑 (b)
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i=0 ; i<cases; i++) {
			int n = Integer.parseInt(br.readLine());
			String input = br.readLine();
			solve1(input);
			}
	}
	public static void solve1(String string) {
		initCube();
		String oriStr = string;
		String[] str = oriStr.split(" ");
		for(int i=0; i<str.length; i++) {
			String[] str1 = str[i].split("");
			for(int j=0; j<str1.length; j+=2) {
				if(str1[j].equals("U")) {
					if(str1[j+1].equals("+")) turnU(true);
					if(str1[j+1].equals("-")) turnU(false);
				}
				if(str1[j].equals("D")) {
					if(str1[j+1].equals("+")) turnD(false);
					if(str1[j+1].equals("-")) turnD(true);
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
		//StringBuilder sb = new StringBuilder("");
		//for(int c=0; c<6; c++) {
		for(int a=0; a<3; a++) {
			StringBuilder sb = new StringBuilder("");
			for (int b=0; b<3; b++) {
				sb.append(cube[0][a][b]);
			}
			sb.append("\n");
			System.out.print(sb.toString());
		}
		//}
		//return sb.toString();
	}
	private static void turnU (Boolean flag) {
		// '+' 시계방향
		if(flag) {
			String temp[] = new String[9];
			//cube[2][0][0] -> cube[4][0][0]
			//cube[2][0][1] -> cube[4][0][1]
			//cube[2][0][2] -> cube[4][0][2]
			for(int i=0; i<3; i++) {
				temp[i] = cube[4][0][i];
				cube[4][0][i] = cube[2][0][i];
			}
			//cube[4][0][0] -> cube[3][0][0]
			//cube[4][0][1] -> cube[3][0][1]
			//cube[4][0][2] -> cube[3][0][2]
			for(int i=0; i<3; i++) {
				temp[i+3] = cube[3][0][i];
				cube[3][0][i] = temp[i];
			}
			//cube[3][0][0] -> cube[5][0][0]
			//cube[3][0][1] -> cube[5][0][1]
			//cube[3][0][2] -> cube[5][0][2]
			for(int i=0; i<3; i++) {
				temp[i+6] = cube[5][0][i];
				cube[5][0][i] = temp[i+3];
			}
			//cube[5][0][0] -> cube[2][0][0]
			//cube[5][0][1] -> cube[2][0][1]
			//cube[5][0][2] -> cube[2][0][2]
			for(int i=0; i<3; i++) {
				cube[2][0][i] = temp[i+6];
			}
		} else {
			String temp[] = new String[9];
			//cube[2][0][0] -> cube[5][0][0]
			//cube[2][0][1] -> cube[5][0][1]
			//cube[2][0][2] -> cube[5][0][2]
			for(int i=0; i<3; i++) {
				temp[i] = cube[5][0][i];
				cube[5][0][i] = cube[2][0][i];
			}
			//cube[5][0][0] -> cube[3][0][0]
			//cube[5][0][1] -> cube[3][0][1]
			//cube[5][0][2] -> cube[3][0][2]
			for(int i=0; i<3; i++) {
				temp[i+3] = cube[3][0][i];
				cube[3][0][i] = temp[i];
			}
			//cube[3][0][0] -> cube[4][0][0]
			//cube[3][0][1] -> cube[4][0][1]
			//cube[3][0][2] -> cube[4][0][2]
			for(int i=0; i<3; i++) {
				temp[i+6] = cube[4][0][i];
				cube[4][0][i] = temp[i+3];
			}
			//cube[4][0][0] -> cube[2][0][0]
			//cube[4][0][1] -> cube[2][0][1]
			//cube[4][0][2] -> cube[2][0][2]
			for(int i=0; i<3; i++) {
				cube[2][0][i] = temp[i+6];
			}
		}
		// 자체 회전
		turnPlate(0, flag);
	}
	private static void turnD (Boolean flag) {
		// '+' 시계방향
		if(flag) {
			String temp[] = new String[9];
			//cube[2][2][0] -> cube[5][2][0]
			//cube[2][2][1] -> cube[5][2][1]
			//cube[2][2][2] -> cube[5][2][2]
			for(int i=0; i<3; i++) {
				temp[i] = cube[5][0][i];
				cube[5][2][i] = cube[2][2][i];
			}
			//cube[5][2][0] -> cube[3][2][0]
			//cube[5][2][1] -> cube[3][2][1]
			//cube[5][2][2] -> cube[3][2][2]
			for(int i=0; i<3; i++) {
				temp[i+3] = cube[3][2][i];
				cube[3][2][i] = temp[i];
			}
			//cube[3][2][0] -> cube[4][2][0]
			//cube[3][2][1] -> cube[4][2][1]
			//cube[3][2][2] -> cube[4][2][2]
			for(int i=0; i<3; i++) {
				temp[i+6] = cube[4][2][i];
				cube[4][2][i] = temp[i+3];
			}
			//cube[4][2][0] -> cube[2][2][0]
			//cube[4][2][1] -> cube[2][2][1]
			//cube[4][2][2] -> cube[2][2][2]
			for(int i=0; i<3; i++) {
				cube[2][2][i] = temp[i+6];
			}
		} else {
			String temp[] = new String[9];
			//cube[2][2][0] -> cube[4][2][0]
			//cube[2][2][1] -> cube[4][2][1]
			//cube[2][2][2] -> cube[4][2][2]
			for(int i=0; i<3; i++) {
				temp[i] = cube[4][2][i];
				cube[4][2][i] = cube[2][2][i];
			}
			//cube[4][2][0] -> cube[3][2][0]
			//cube[4][2][1] -> cube[3][2][1]
			//cube[4][2][2] -> cube[3][2][2]
			for(int i=0; i<3; i++) {
				temp[i+3] = cube[3][2][i];
				cube[3][2][i] = temp[i];
			}
			//cube[3][2][0] -> cube[5][2][0]
			//cube[3][2][1] -> cube[5][2][1]
			//cube[3][2][2] -> cube[5][2][2]
			for(int i=0; i<3; i++) {
				temp[i+6] = cube[5][2][i];
				cube[5][2][i] = temp[i+3];
			}
			//cube[5][2][0] -> cube[2][2][0]
			//cube[5][2][1] -> cube[2][2][1]
			//cube[5][2][2] -> cube[2][2][2]
			for(int i=0; i<3; i++) {
				cube[2][2][i] = temp[i+6];
			}
		} 
		// 자체 회전
		turnPlate(1, flag);
	}
	private static void turnF (Boolean flag) {
		// '+' 시계방향
		if(flag) {
			String temp[] = new String[9];
			//cube[0][2][0] -> cube[5][0][0]
			//cube[0][2][1] -> cube[5][1][0]
			//cube[0][2][2] -> cube[5][2][0]
			for(int i=0; i<3; i++) {
				temp[i] = cube[5][i][0];
				cube[5][i][0] = cube[0][2][i];
			}
			//cube[5][0][0] -> cube[1][0][0]
			//cube[5][1][0] -> cube[1][0][1]
			//cube[5][2][0] -> cube[1][0][2]
			for(int i=0; i<3; i++) {
				temp[i+3]=cube[1][0][i];
				cube[1][0][i] = temp[i];
			}
			//cube[1][0][0] -> cube[4][2][2]
			//cube[1][0][1] -> cube[4][1][2]
			//cube[1][0][2] -> cube[4][0][2]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[4][2-i][2];
				cube[4][2-i][2] = temp[i+3];
			}
			//cube[4][2][2] -> cube[0][2][0]
			//cube[4][1][2] -> cube[0][2][1]
			//cube[4][0][2] -> cube[0][2][2]
			for(int i=0; i<3; i++) {
				cube[0][2][i] = temp[i+6];
			}
		} else {
			String temp[] = new String[9];
			//cube[0][2][0] -> cube[4][2][2]
			//cube[0][2][1] -> cube[4][1][2]
			//cube[0][2][2] -> cube[4][0][2]
			for(int i=0; i<3; i++) {
				temp[i] = cube[4][2-i][2];
				cube[4][2-i][2] = cube[0][2][i];
			}
			//cube[4][2][2] -> cube[1][0][0]
			//cube[4][1][2] -> cube[1][0][1]
			//cube[4][0][2] -> cube[1][0][2]
			for(int i=0; i<3; i++) {
				temp[i+3]=cube[1][0][i];
				cube[1][0][i] = temp[i];
			}
			//cube[1][0][0] -> cube[5][0][0]
			//cube[1][0][1] -> cube[5][1][0]
			//cube[1][0][2] -> cube[5][2][0]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[5][i][0];
				cube[5][i][0] = temp[i+3];
			}
			//cube[5][0][0] -> cube[0][2][0]
			//cube[5][1][0] -> cube[0][2][1]
			//cube[5][2][0] -> cube[0][2][2]
			for(int i=0; i<3; i++) {
				cube[0][2][i] = temp[i+6];
			}
		}
		// 자체 회전
		turnPlate(2, flag);
	}
	private static void turnB (Boolean flag) {
		// '+' 시계방향
		if(flag) {
			String temp[] = new String[9];
			//cube[0][0][0] -> cube[4][2][0]
			//cube[0][0][1] -> cube[4][1][0]
			//cube[0][0][2] -> cube[4][0][0]
			for(int i=0; i<3; i++) {
				temp[i] = cube[4][2-i][0];
				cube[4][2-i][0] = cube[0][0][i];
			}
			//cube[4][2][0] -> cube[1][2][2]
			//cube[4][1][0] -> cube[1][2][1]
			//cube[4][0][0] -> cube[1][2][0]
			for(int i=0; i<3; i++) {
				temp[i+3]=cube[1][2][2-i];
				cube[1][2][2-i] = temp[i];
			}
			//cube[1][2][2] -> cube[5][0][2]
			//cube[1][2][1] -> cube[5][1][2]
			//cube[1][2][0] -> cube[5][2][2]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[5][i][2];
				cube[5][i][2] = temp[i+3];
			}
			//cube[5][0][2] -> cube[0][0][0]
			//cube[5][1][2] -> cube[0][0][1]
			//cube[5][2][2] -> cube[0][0][2]
			for(int i=0; i<3; i++) {
				cube[0][0][i] = temp[i+6];
			}
		} else {
			String temp[] = new String[9];
			//cube[0][0][0] -> cube[5][0][2]
			//cube[0][0][1] -> cube[5][1][2]
			//cube[0][0][2] -> cube[5][2][2]
			for(int i=0; i<3; i++) {
				temp[i] = cube[5][i][2];
				cube[5][i][2] = cube[0][0][i];
			}
			//cube[5][0][2] -> cube[1][2][2]
			//cube[5][1][2] -> cube[1][2][1]
			//cube[5][2][2] -> cube[1][2][0]
			for(int i=0; i<3; i++) {
				temp[i+3]=cube[1][2][2-i];
				cube[1][2][2-i] = temp[i];
			}
			//cube[1][2][2] -> cube[4][2][0]
			//cube[1][2][1] -> cube[4][1][0]
			//cube[1][2][0] -> cube[4][0][0]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[4][2-i][0];
				cube[4][2-i][0] = temp[i+3];
			}
			//cube[4][2][0] -> cube[0][0][0]
			//cube[4][1][0] -> cube[0][0][1]
			//cube[4][0][0] -> cube[0][0][2]
			for(int i=0; i<3; i++) {
				cube[0][0][i] = temp[i+6];
			}
		}
		// 자체 회전
		turnPlate(3, flag);
	}
	private static void turnL (Boolean flag) {
		// '+' 시계방향
		if(flag) {
			String temp[] = new String[9];
			//cube[0][0][0] -> cube[2][0][0]
			//cube[0][1][0] -> cube[2][1][0]
			//cube[0][2][0] -> cube[2][2][0]
			for(int i=0; i<3; i++) {
				temp[i] = cube[2][i][0];
				cube[2][i][0] = cube[0][i][0];
			}
			//cube[2][0][0] -> cube[1][0][0]
			//cube[2][1][0] -> cube[1][1][0]
			//cube[2][2][0] -> cube[1][2][0]
			for(int i=0; i<3; i++) {
				temp[i+3]=cube[1][i][0];
				cube[1][i][0] = temp[i];
			}
			//cube[1][0][0] -> cube[3][2][2]
			//cube[1][1][0] -> cube[3][1][2]
			//cube[1][2][0] -> cube[3][0][2]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[3][2-i][2];
				cube[3][2-i][2] = temp[i+3];
			}
			//cube[3][2][2] -> cube[0][0][0]
			//cube[3][1][2] -> cube[0][1][0]
			//cube[3][0][2] -> cube[0][2][0]
			for(int i=0; i<3; i++) {
				cube[0][i][0] = temp[i+6];
			}
		} else {
			String temp[] = new String[9];
			//cube[0][0][0] -> cube[3][2][2]
			//cube[0][1][0] -> cube[3][1][2]
			//cube[0][2][0] -> cube[3][0][2]
			for(int i=0; i<3; i++) {
				temp[i] = cube[3][2-i][2];
				cube[3][2-i][2] = cube[0][i][0];
			}
			//cube[3][2][2] -> cube[1][0][0]
			//cube[3][1][2] -> cube[1][1][0]
			//cube[3][0][2] -> cube[1][2][0]
			for(int i=0; i<3; i++) {
				temp[i+3]=cube[1][i][0];
				cube[1][i][0] = temp[i];
			}
			//cube[1][0][0] -> cube[2][0][0]
			//cube[1][1][0] -> cube[2][1][0]
			//cube[1][2][0] -> cube[2][2][0]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[2][i][0];
				cube[2][i][0] = temp[i+3];
			}
			//cube[2][0][0] -> cube[0][0][0]
			//cube[2][1][0] -> cube[0][1][0]
			//cube[2][2][0] -> cube[0][2][0]
			for(int i=0; i<3; i++) {
				cube[0][i][0] = temp[i+6];
			}
		}
		// 자체 회전
		turnPlate(4, flag);
	}
	private static void turnR (Boolean flag) {
		// '+' 시계방향
		if(flag) {
			String temp[] = new String[9];
			//cube[0][0][2] -> cube[3][2][0]
			//cube[0][1][2] -> cube[3][1][0]
			//cube[0][2][2] -> cube[3][0][0]
			for(int i=0; i<3; i++) {
				temp[i] = cube[3][2-i][0];
				cube[3][2-i][0] = cube[0][i][2];
			}
			//cube[3][2][0] -> cube[1][0][2]
			//cube[3][1][0] -> cube[1][1][2]
			//cube[3][0][0] -> cube[1][2][2]
			for(int i=0; i<3; i++) {
				temp[i+3]=cube[1][i][2];
				cube[1][i][2] = temp[i];
			}
			//cube[1][0][2] -> cube[2][0][2]
			//cube[1][1][2] -> cube[2][1][2]
			//cube[1][2][2] -> cube[2][2][2]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[2][i][2];
				cube[2][i][2] = temp[i+3];
			}
			//cube[2][0][2] -> cube[0][0][2]
			//cube[2][1][2] -> cube[0][1][2]
			//cube[2][2][2] -> cube[0][2][2]
			for(int i=0; i<3; i++) {
				cube[0][i][2] = temp[i+6];
			}
		} else {
			String temp[] = new String[9];
			//cube[0][0][2] -> cube[2][0][2]
			//cube[0][1][2] -> cube[2][1][2]
			//cube[0][2][2] -> cube[2][2][2]
			for(int i=0; i<3; i++) {
				temp[i] = cube[2][i][2];
				cube[2][i][2] = cube[0][i][2];
			}
			//cube[2][0][2] -> cube[1][0][2]
			//cube[2][1][2] -> cube[1][1][2]
			//cube[2][2][2] -> cube[1][2][2]
			for(int i=0; i<3; i++) {
				temp[i+3]=cube[1][i][2];
				cube[1][i][2] = temp[i];
			}
			//cube[1][0][2] -> cube[3][2][0]
			//cube[1][1][2] -> cube[3][1][0]
			//cube[1][2][2] -> cube[3][0][0]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[3][2-i][0];
				cube[3][2-i][0] = temp[i+3];
			}
			//cube[3][2][0] -> cube[0][0][2]
			//cube[3][1][0] -> cube[0][1][2]
			//cube[3][0][0] -> cube[0][2][2]
			for(int i=0; i<3; i++) {
				cube[0][i][2] = temp[i+6];
			}
		}
		// 자체 회전
		turnPlate(5, flag);
	}
	
	// 자체회전
	private static void turnPlate(int n, boolean flag) {
		ArrayList<String>temp = new ArrayList<String>();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) temp.add(cube[n][i][j]);
		}
		//시계방향
		if(flag) {
			cube[n][0][0] = temp.get(6);cube[n][0][1] = temp.get(3);cube[n][0][2] = temp.get(0);
			cube[n][1][0] = temp.get(7);cube[n][1][1] = temp.get(4);cube[n][1][2] = temp.get(1);
			cube[n][2][0] = temp.get(8);cube[n][2][1] = temp.get(5);cube[n][2][2] = temp.get(2);
		} else {
			cube[n][0][0] = temp.get(2);cube[n][0][1] = temp.get(5);cube[n][0][2] = temp.get(8);
			cube[n][1][0] = temp.get(1);cube[n][1][1] = temp.get(4);cube[n][1][2] = temp.get(7);
			cube[n][2][0] = temp.get(0);cube[n][2][1] = temp.get(3);cube[n][2][2] = temp.get(6);
		}
	}
}
