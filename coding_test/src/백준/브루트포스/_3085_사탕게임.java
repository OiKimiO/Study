package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _3085_사탕게임 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] arr = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i - 1 >= 0) {
					if(arr[i][j] == arr[i-1][j]) continue;
					char swap   = arr[i][j];
					arr[i][j]   = arr[i-1][j];
					arr[i-1][j] = swap;
					
					
				}
			}
		}
	}
	
}
