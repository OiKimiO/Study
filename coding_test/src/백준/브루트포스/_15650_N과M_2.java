package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _15650_N과M_2 {

	static boolean[] check;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		check = new boolean[N];
		arr   = new int[M];
		
		dfs(N,M,0,0);
		System.out.println(sb.toString());
	}

	private static void dfs(int n, int m, int start, int depth) {
		if(m == depth) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(check[i]) continue;
			check[i] = true;
			arr[depth] = (i+1);
			dfs(n, m, i, depth+1);
			check[i] = false;
		}
	}

}
