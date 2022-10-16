package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1309_동물원_다시풀어보기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] Zoo_DP = new int[N+1][3];
		Zoo_DP[1][0] = 1;
		Zoo_DP[1][1] = 1;
		Zoo_DP[1][2] = 1;
		
		for(int i = 2; i <= N; i++ ) {
			Zoo_DP[i][0] = Zoo_DP[i-1][0] + Zoo_DP[i-1][1] + Zoo_DP[i-1][2];
			Zoo_DP[i][1] = Zoo_DP[i-1][0] + Zoo_DP[i-1][2];
			Zoo_DP[i][2] = Zoo_DP[i-1][0] + Zoo_DP[i-1][1];
		}
		
		System.out.println(Zoo_DP[N][0]+Zoo_DP[N][1]+Zoo_DP[N][2]);
	}

}
