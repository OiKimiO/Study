package 백준.DP;

import java.io.*;
import java.util.*;

public class _11055_가장큰증가부분수열_다시풀기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N     = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp  = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			dp[i] = arr[i];
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && dp[i] < dp[j] + arr[i]) {
					dp[i] = dp[j] + arr[i]; 
				}
			}
			max = Math.max(dp[i], max);
		}
		
		System.out.println(max);
	}

}
