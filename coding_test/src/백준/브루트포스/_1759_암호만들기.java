package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _1759_암호만들기 {

	static char[] arr_char;
	static char[] com_char;
	static boolean[] check_bool;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr_char   = new char[L];
		com_char   = new char[N];
		check_bool = new boolean[L];
		
		for(int i = 0; i < L; i++){
			arr_char[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr_char);
		
		dfs(N,L,0,0);
		
		System.out.println(sb.toString());
	}

	private static void dfs(int N, int L, int start, int depth) {
		
		if(depth == N) {
			if(check_answer(N)) {
				sb.append(com_char).append('\n');
			};
			return;
		}
		
		for(int i = start; i < L; i++) {
			if(check_bool[i]) continue;
			check_bool[i] = true;
			com_char[depth] = arr_char[i];
			dfs(N,L,i+1,depth+1);
			check_bool[i] = false;
		}
	}

	private static boolean check_answer(int N) {
		int ja_count = 0;
		int mo_count = 0;
		for(int i = 0; i < N; i++) {
			switch(com_char[i]) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					mo_count++;
					break;
				default :
					ja_count++;
					break;
			}
		}
		if(ja_count >= 2 && mo_count >= 1) {
			return true;
		}else {
			return false;
		}
	}
}
