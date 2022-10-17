package 백준.DP;

import java.util.*;
import java.io.*;

public class _10844_쉬운계단수_다시풀기 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][9];
		
		for(int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			// dp[i][0] = 1;
			for(int j = 0; j < i; j++) {
				if(j-1 >= 0 && j+1 < 10){
					dp[i][j] += dp[i-1][j-1] + dp[i-1][j+1];
				}else if(j-1 < 0) {
					dp[i][j] += dp[i-1][j+1];
				}else if(j+1 > 9) {
					dp[i][j] += dp[i-1][j-1];
				}
			}
		}
	}

}
