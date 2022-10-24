package 백준.DP;

import java.io.*;
import java.util.*;

public class _11054_가장긴바이토닉부분수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr         = new int[N];
		int[] increase_dp = new int[N];
		int[] decrease_dp = new int[N];
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 증가하는 부분수열
		for(int i = 0; i < N; i++) {
			increase_dp[i] = 1;
			
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && increase_dp[i] < increase_dp[j] + 1) {
					increase_dp[i] = increase_dp[j] + 1;
				}
			}
			
		}
		
		// 감소하는 부분수열
		for(int i = N-1; i >= 0; i--) {
			decrease_dp[i] = 1;
			
			for(int j = N-1; j > i; j--) {
				if(arr[i] > arr[j] && decrease_dp[i] < decrease_dp[j] + 1) {
					decrease_dp[i] = decrease_dp[j] + 1;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			max = Math.max(decrease_dp[i]+increase_dp[i]-1, max);
		}
		
		System.out.println(max);
	}
}
