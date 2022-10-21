package 백준.DP;

import java.io.*;
import java.util.*;

public class _9465스티커 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T > 0) {
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[2][N];
			
			for(int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(N > 1) {
				dp[0][1] += dp[1][0];
				dp[1][1] += dp[0][0];
			}
			
			for(int i = 2; i < N; i++) {
				int org_num  = dp[0][i];
				int org_num2 = dp[1][i];
				dp[0][i] = org_num + Math.max(dp[0][i-2], dp[1][i-2]);
				dp[0][i] = Math.max(org_num + dp[1][i-1], dp[0][i]);
				dp[1][i] = org_num2 + Math.max(dp[0][i-2], dp[1][i-2]);
				dp[1][i] = Math.max(org_num2 + dp[0][i-1], dp[1][i]);
			}
			
			sb.append(dp[0][N-1] > dp[1][N-1] ? dp[0][N-1] : dp[1][N-1]).append('\n');
			T--;
		}
		
		System.out.println(sb.toString());
	}
}
