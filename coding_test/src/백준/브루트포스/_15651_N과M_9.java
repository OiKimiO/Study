package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _15651_N과M_9 {

	static StringBuilder sb = new StringBuilder();
	static boolean[] check;
	static int[] arr;
	static int[] print;
	static HashSet<String> set = new HashSet<>();
	
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
		
		dfs(N,M,0);
		System.out.println(sb.toString());
		
	}

	private static void dfs(int n, int m, int depth) {
		if(depth == m) {
			StringBuilder sb1 = new StringBuilder();
			for(int i = 0; i < m; i++) {
				sb1.append(print[i] + " ");
			}
			
			if(!set.contains(sb1.toString())) {
				sb.append(sb1.toString()).append('\n');
				set.add(sb1.toString());
			}
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(check[i])continue;
			check[i] = true;
			print[depth] = arr[i];
			dfs(n,m,depth+1);
			check[i] = false;
		}
	}

}
