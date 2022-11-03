package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _1107_리모컨 {

	static boolean[] broken;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int answer=Integer.MAX_VALUE;
		StringTokenizer st = null;
		
		if(M > 0) {
			st = new StringTokenizer(br.readLine());
		}
		
		broken = new boolean[10];
		
		for(int i = 0; i < M; i++) {
			broken[Integer.parseInt(st.nextToken())] = true;
		}
		int press = N - 100;
		
		if(press < 0) {
			answer = -press;
		}else {
			answer = press;
		}
		
		for(int i = 0; i < 1000000; i++) {
			int C = i;
			int length = possible(C);
			
			if(length > 0) {
				press = N - C;
				
				if(press < 0) {
					press = -press;
				}
				
				if(answer > length + press) {
					answer = length + press;
				}
			}
		}
		System.out.println(answer);
	}

	private static int possible(int c) {
		if(c == 0) {
			if(broken[0]) {
				return 0;
			}else {
				return 1;
			}
		}
		
		int length = 0;
		
		while(c > 0) {
			if(broken[c%10]) {
				return 0;
			}
			length++;
			c /= 10;
		}
		
		return length;
	}

}
