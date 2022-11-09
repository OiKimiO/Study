package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _15651_N과M_4 {

	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static boolean[] check;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		arr   = new int[M];
		check = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		dfs(N,M,0,0);
		System.out.println(sb.toString());
		
	}

	private static void dfs(int n, int m, int start, int depth) {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(check[i]) continue;
			check[i] = true;
			arr[depth] = i+1;
			dfs(n,m,i,depth+1);
		}
	}

}
