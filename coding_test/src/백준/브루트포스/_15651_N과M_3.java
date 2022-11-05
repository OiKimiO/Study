package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _15651_N과M_3 {

	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr   = new int[M];
		
		dfs(N,M,0);
		System.out.println(sb.toString());
		
	}

	private static void dfs(int n, int m, int depth) {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 0; i < n; i++) {
			arr[depth] = i+1;
			dfs(n,m,depth+1);
		}
	}

}
