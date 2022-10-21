package 백준.DP;

import java.io.*;
import java.util.*;



public class _14002_가장긴증가하는부분수열4_틀림 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp  = new int[N];
		Stack<Integer> stk = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			dp[i] = 1;
			min = Math.min(min, arr[i]);
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					if(stk.isEmpty() || arr[stk.peek()] < arr[i]) {
						stk.add(i);
					}
				}
			}
		}
		
		
		System.out.println(dp[stk.peek()]);
		
		while(!stk.isEmpty()) {
			sb.insert(0,arr[stk.pop()] + " ");
		}
		
		sb.insert(0,min + " ");
		
		
		System.out.println(sb.toString());
		
		
	}
}
