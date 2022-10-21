package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1699_제곱수의합_다시풀기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N    = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			dp[i] = 100000;
			for(int j = 1; j*j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i-(j*j)] + 1);
			}
		}
		
		System.out.println(dp[N]);
	}

}
