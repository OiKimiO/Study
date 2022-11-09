package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _10819_차이를최대로_틀림 {

	private static int[] arr;
	private static boolean[] check;
	private static int maxSum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr   = new int[N];
		check = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		
		System.out.println(maxSum);
	}
	private static void dfs(int sum) {
		for(int i = 0; i < arr.length-1; i++) {
			if(check[i])continue;
			check[i] = true;
			dfs(sum+arr[i]);
			check[i] = false;
		}
	}
	

}
