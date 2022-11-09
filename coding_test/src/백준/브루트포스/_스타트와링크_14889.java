package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _스타트와링크_14889 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(N,0);
	}

	private static void dfs(int n, int i) {
		// TODO Auto-generated method stub
		
	}
}
