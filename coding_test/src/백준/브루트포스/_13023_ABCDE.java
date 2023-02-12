package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _13023_ABCDE {
	static int[][] cost;
	static boolean[] visited;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N  = Integer.parseInt(st.nextToken());
		int M  = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		cost = new int[N][N];
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			cost[row][col] = 1;
			cost[col][row] = 1;
		}
		
		for(int i = 0; i < N; i++) {
			dfs(i, 0, 0, N);
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int start, int end, int total, int N) {
		if(N == start || N == end) {
			return;
		}
		
		if(visited[end]) {
			return;
		}
		
		if(total == 5) {
			answer = 1;
		}
		
		visited[end] = true;
		if(cost[start][end] == 0) {
			dfs(end, start+1, total, N);
		}else {
			dfs(end, start+1, total+1, N);
		}
		visited[end] = false;
	}

}
