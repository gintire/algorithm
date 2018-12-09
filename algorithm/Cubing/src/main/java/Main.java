import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
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
	
	//U : Èò(w), D : ³ë(y), F : »¡(r), B : ¿À·»Áö(o), L : ÃÊ·Ï(g), R : ÆÄ¶û (b)
	public static void solve(String string) {
		int cases = 1;
		for (int i=0 ; i<cases; i++) {
			solve1(string);
			}
	}
	public static String solve1(String string) {
		initCube();
		String oriStr = string;
		String[] str = oriStr.split("");
		String resStr = "";
		for(int j=0; j<str.length; j+=2) {
			if(str[j].equals("L")) {
				if(str[j+1].equals("+")) turnU(true);
				if(str[j+1].equals("-")) turnU(false);
			}
			for(int a=0; a<3; a++) {
				for (int b=0; b<3; b++) {
					resStr+=cube[0][a][b];
				}
			}
		}
		return resStr;
	}
	private static void turnU (Boolean flag) {
		// '+' ½Ã°è¹æÇâ
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
			//cube[1][0][0] -> cube[3][0][0]
			//cube[1][1][0] -> cube[3][1][0]
			//cube[1][2][0] -> cube[3][2][0]
			for(int i=0; i<3; i++) {
				temp[i+6]=cube[3][i][0];
				cube[3][i][0] = temp[i+3];
			}
			//cube[3][0][0] -> cube[0][0][0]
			//cube[3][1][0] -> cube[0][1][0]
			//cube[3][2][0] -> cube[0][2][0]
			for(int i=0; i<3; i++) {
				cube[0][i][0] = temp[i+6];
			}
		} else {
			String temp[] = new String[9];
			//cube[0][0][0] -> cube[3][0][0]
			//cube[0][1][0] -> cube[3][1][0]
			//cube[0][2][0] -> cube[3][2][0]
			for(int i=0; i<3; i++) {
				temp[i] = cube[3][i][0];
				cube[3][i][0] = cube[0][i][0];
			}
			//cube[3][0][0] -> cube[1][0][0]
			//cube[3][1][0] -> cube[1][1][0]
			//cube[3][2][0] -> cube[1][2][0]
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
	}
	private static void turnD (Boolean flag) {
		
	}
	private static void turnF (Boolean flag) {
		
	}
	private static void turnB (Boolean flag) {
	
	}
	private static void turnL (Boolean flag) {
		
	}
	private static void turnR (Boolean flag) {
	
	}
	
	private static void swap (String a, String b) {
		String temp = b;
		b = a;
		a = temp;
	}
}
