package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10819_차이를최대로_틀림 {
	static int[] T;
	static int[] P;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		T  = new int[N];
		P  = new int[N];
		dp = new int[N+1];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			int next_idx = i + T[i]; 
			int cur_DP   = dp[i];
			int cur_P    = P[i];
			int next_DP  = dp[next_idx]; 
			if(next_idx <= N) {
				dp[next_idx] = Math.max(next_DP, cur_DP+cur_P);
			}
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		System.out.println(dp[N]);
	}
}
