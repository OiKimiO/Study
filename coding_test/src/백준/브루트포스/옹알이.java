package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class 옹알이 {

	static HashMap<String, Integer> map = new HashMap<>();
	static boolean[] check;
	static String[] arr = {"aya", "ye", "woo", "ma"};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		check = new boolean[4];
		
		dfs("");
		System.out.println(map);		
	}

	private static void dfs(String print) {
		if(print != "") {
			map.put(print, map.getOrDefault(print, 0)+1);
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(check[i]) continue;
			check[i] = true;
			dfs(print+arr[i]);
			check[i] = false;
		}
	}
	
	
}
