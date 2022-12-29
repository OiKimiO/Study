package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _6603_로또 {
	static int[] arr;
	static boolean[] visited;
	static int[] answer = new int[6];
	static StringBuilder StringAnwer = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str 		  = br.readLine();
		while(str.length() > 1) {
			StringTokenizer st = new StringTokenizer(str);
			int k       	   = Integer.parseInt(st.nextToken());
			arr         	   = new int[k];
			StringAnwer 	   = new StringBuilder();
			visited     	   = new boolean[k];
			
			for(int i = 0; i < k; i++){
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			SelectRandom(0,0,0,k);
			System.out.println(StringAnwer.toString());
			
			str = br.readLine();
		}
		
		
	}
	
	public static void SelectRandom(int start, int cost,int answeridx, int total) {
		if(cost == 6) {
			print();
			return;
		}
		
		for(int i = start; i < total; i++) {
			if(visited[i]) {
				continue;
			}
			visited[i] = true;
			answer[answeridx] = arr[i];
			SelectRandom(i+1, cost+1, answeridx+1, total);	
			visited[i] = false;
		}
	}
	
	public static void print() {
		for(int i = 0; i < 6; i++) {
			StringAnwer.append(answer[i] + " ");
		}
		StringAnwer.append('\n');
	}
}
