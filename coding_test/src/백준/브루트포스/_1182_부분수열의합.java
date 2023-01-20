package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _1182_부분수열의합 {
	static int[] arr;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0,0,N,S);
		
		if(S == 0) {
			answer--;
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int sum,int start_idx, int cost, int N, int S) {
		
		if(start_idx == 5) {
			System.out.printf("[cost::%d] idx::%d, sum::%d \n\n",cost, start_idx, sum);
		}else if(start_idx-1 >= 0){
			System.out.printf("[cost::%d] idx::%d, arr[%d]::%d ,sum::%d \n",cost, start_idx, start_idx-1, arr[start_idx-1], sum);
		}
		
		if(N == cost) {
			if(sum == S){
				answer++;
			}
			return;
		}
		
		// 
		dfs(sum,start_idx+1, cost+1, N, S);
		dfs(sum + arr[start_idx],start_idx+1, cost+1, N, S);
	}

}
