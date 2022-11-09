package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _14501_퇴사 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N];
		int[] P = new int[N];
		int[] dp = new int[N+1];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			int next_idx = i+T[i];
			if(next_idx <= N) {
				dp[next_idx] = Math.max(dp[i] + P[i], dp[next_idx]);
			}
			// 현재의 값이 다음값을 비교해 더 큰값을 선택해 다음 값으로 넣음
			// 현재는 0일 경우를 생각
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		System.out.println(dp[N]);
	}
}
