package 백준.DP;

import java.io.*;
import java.util.*;

public class _1309동물원 {
	private static int empty = 0;
	private static int left  = 1;
	private static int right = 2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N      = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][3];
		
		dp[0][left]  = 1;
		dp[0][right] = 1;
		dp[0][empty] = 1;
		
		for(int i = 1; i < N; i++) {
			dp[i][empty] = (dp[i-1][left]  + dp[i-1][right]+dp[i-1][empty])%9901;
			dp[i][left]  = (dp[i-1][right] + dp[i-1][empty])%9901;
			dp[i][right] = (dp[i-1][left]  + dp[i-1][empty])%9901;
		}
		
		System.out.println((dp[N-1][empty]+dp[N-1][left]+dp[N-1][right]) % 9901);
	}
}
