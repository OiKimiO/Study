package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _1759_암호만들기_2번째시도 {

	static char[] arr_char;
	static char[] arr_answer;
	static boolean[] visited;
	static StringBuilder answer = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		arr_char = new char[C];
		visited  = new boolean[C];
		arr_answer = new char[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < C; i++) {
			arr_char[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr_char);
		
		dfs(0,0,C,N,0);
		
		System.out.println(answer.toString());
	}

	private static void dfs(int start, int ansidx, int total, int costtoal,int cost) {
		if(cost == costtoal) {
			validateAnswer();
			return;
		}
		
		for(int i = start; i < total; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			arr_answer[ansidx] = arr_char[i];
			dfs(i+1, ansidx+1, total, costtoal, cost+1);
			visited[i] = false;
		}
	}

	private static void validateAnswer() {
		int mo = 0;
		int ja = 0;
		for(int i = 0; i < arr_answer.length; i++) {
			switch(arr_answer[i]) {
				case 'a' :
				case 'e' :
				case 'i' :
				case 'o' :
				case 'u' :
					mo++;
					break;
				default : 
					ja++;
					break;
			}
		}
		
		if(mo >= 1 && ja >= 2) {
			for(int i = 0; i < arr_answer.length; i++) {
				answer.append(arr_answer[i]);
			}
			answer.append('\n');
		}
	}

}
