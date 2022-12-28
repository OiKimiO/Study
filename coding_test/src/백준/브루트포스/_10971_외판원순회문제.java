package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _10971_외판원순회문제 {
	
	static boolean[] visited = null;
	static int[][] arr_cost = null;
	static int sum_cost = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		visited  = new boolean[N];
		arr_cost = new int[N][N];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			for(int j = 0; j < N; j++) {
				arr_cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, N, 0, 0);
		
		System.out.println(sum_cost);
	}

	private static void dfs(int start, int destination, int N, int cost, int sum) {
		
		if(cost == N) {	
			if(sum_cost > sum) {
				sum_cost = sum;
			}
			return;
		}
		
		for(int end = 0; end < N; end++) {
			if(arr_cost[start][end] == 0 ) {
				continue;
			}
			
			if(destination == end && cost+1 < N) {
				continue;
			}
			
			if(visited[end]) {
				continue;
			}
			
			visited[end] = true;
			
			dfs(end,destination, N, cost+1, sum+arr_cost[start][end]);
			
			visited[end] = false;
		}
	}
}
