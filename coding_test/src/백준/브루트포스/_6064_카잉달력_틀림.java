package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _6064_카잉달력_틀림 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			boolean check = false;
			for(int k = x; k < M*N; k+=M) {
				if(k%N == y) {
					sb.append(k+1);
					check= true;
					break;
				}
			}
			
			if(check) {
				sb.append('\n');
			}else {
				sb.append("-1").append('\n');
			}
			
			T--;
		}
		
		System.out.println(sb.toString());
	}
	
	public static int gcd(int a, int b) {
		if(a%b == 0) {
			return b;
		}else {
			return gcd(b, a%b);		
		}
	}
}
