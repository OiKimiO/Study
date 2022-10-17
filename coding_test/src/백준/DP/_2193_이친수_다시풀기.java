package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2193_이친수_다시풀기 {

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 91;
		int[][] dp = new int[N][3];
		dp[1][1] = 1;
		dp[2][0] = 1;
		dp[3][0] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		for(int i = 4; i < N; i++) {
			
		}
		
	}
	
}
