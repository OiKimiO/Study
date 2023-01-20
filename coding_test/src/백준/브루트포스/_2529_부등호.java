package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _2529_부등호 {
	static boolean[] visited = new boolean[10];
	static char[] sign = new char[10];
	static int[] arr;
	static long max = 0;
	static long min = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[K+1];
		
		for(int i = 0; i < K; i++) {
			sign[i] = st.nextToken().charAt(0);
		}
		
		dfs(0,0,K);
		
		System.out.println(max);
		String str = ""+min;
		
		if(str.length() == K) {
			str = "0" + str;
		}
		
		System.out.println(str);
	}

	private static void dfs(int startidx, int cost, int K) {
		if(cost == K+1) {
			compare(K+1);
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			if(visited[i]) {
				continue;
			}
			
			visited[i] = true;
			arr[startidx] = i;
			dfs(startidx+1, cost+1, K);
			visited[i] = false;
		}
	}

	private static void compare(int K) {
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0]);
		for(int i = 0; i < K-1; i++) {
			switch(sign[i]) {
				case '<' :
					if(arr[i] > arr[i+1]) {
						return;
					}
					break;
				case '>' :
					if(arr[i] < arr[i+1]) {
						return;
					}
					break;
			}
			sb.append(arr[i+1]);
		}
		
		long num = Long.parseLong(sb.toString());
		
		if(max < num) {
			max = num;
		}
		
		if(min > num) {
			min = num;
		}
	}
	
}
