package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _11057_오르막수_다시풀기 {

	private static int mod = 10007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] ASC_DP = new int[N+1][10];
		
		for(int i = 0; i <= 9; i++) {
			ASC_DP[1][i] += 1;
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j <= 9; j++) {
				for(int k = 0; k <= j; k++) {
					ASC_DP[i][j] += ASC_DP[i-1][k];
					ASC_DP[i][j] %= mod;
				}
			}
		}
		
		long answer = 0;
		
		for(int i = 0; i < 10; i++) {
			answer += ASC_DP[N][i];
		}
		
		answer %= mod;
		System.out.println(answer);
	}
}
