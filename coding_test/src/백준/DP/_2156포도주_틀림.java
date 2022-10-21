package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _2156포도주_틀림 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		int idx = 0;
		
		while(N > 0) {
			arr[idx] = Integer.parseInt(br.readLine());
			idx++;
			N--;
		}
		
		dp[0] = arr[0];
		dp[1] = arr[0] + arr[1];
		dp[2] = Math.max(dp[1], arr[0] + arr[2]); 
		
		for(int i = 3; i < dp.length; i++) {
			dp[i] = Math.max(Math.max(dp[i-3] + arr[i], dp[i-2] + arr[i]),dp[i-3]+arr[i-1]+arr[i]);
		}
		
		System.out.println(dp[dp.length-1]);
		
	}
}
