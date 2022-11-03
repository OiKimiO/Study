package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _3085_사탕게임_수정중 {
	
	private static int N;
	private static int answer;
	private static char[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		answer = 0;
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i + 1 < N) {
					swap(i,j,i+1,j);
					findMaxLength();
					swap(i+1,j,i,j);
				}
				
				if(j + 1 < N) {
					swap(i,j,i,j+1);
					findMaxLength();
					swap(i,j+1,i,j);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void findMaxLength() {
		
		for(int i = 0; i < N; i++) {
			int cnt = 1;
			for(int j = 0; j < N-1; j++) {
				if(arr[i][j] == arr[i][j+1]) {
					cnt++;
				}else {
					cnt = 1;
				}
				
				if(answer < cnt) {
					answer = cnt;
				}
			}
			
			cnt = 1;
			for(int j = 0; j < N-1; j++) {
				if(arr[j][i] == arr[j+1][i]) {
					cnt++;
				}else {
					cnt = 1;
				}
				if(answer < cnt) {
					answer = cnt;
				}
			}
			
		}
		
	}

	private static void swap(int i, int j, int I, int J) {
		char swap = arr[i][j];
		arr[i][j] = arr[I][J];
		arr[I][J] = swap;
	}
	
}
