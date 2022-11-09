package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _15651_N과M_8 {

	static StringBuilder sb = new StringBuilder();
	static boolean[] check;
	static int[] arr;
	static int[] print;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		check = new boolean[N];
		arr   = new int[N];
		print = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		dfs(N,M,0,0);
		System.out.println(sb.toString());
		
	}

	private static void dfs(int n, int m,int start, int depth) {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				sb.append(print[i] + " ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = start; i < n; i++) {
			print[depth] = arr[i];
			dfs(n,m,i,depth+1);
		}
	}

}
