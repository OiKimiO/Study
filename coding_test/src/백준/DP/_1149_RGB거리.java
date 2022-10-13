package 백준.DP;

import java.util.*;
import java.io.*;

public class _1149_RGB거리 {

	private static int Green = 1;
	private static int Blue  = 2;
	private static int Red   = 3;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] RGB_DP= new int[N+1][4];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			RGB_DP[i][Green] = Integer.parseInt(st.nextToken());
			RGB_DP[i][Blue]  = Integer.parseInt(st.nextToken());
			RGB_DP[i][Red]   = Integer.parseInt(st.nextToken());
			
			if(i > 1) {
				RGB_DP[i][Green] += Math.min(RGB_DP[i-1][Blue], RGB_DP[i-1][Red]);
				RGB_DP[i][Blue]  += Math.min(RGB_DP[i-1][Green], RGB_DP[i-1][Red]);
				RGB_DP[i][Red]   += Math.min(RGB_DP[i-1][Green], RGB_DP[i-1][Blue]);
			}
		}
		
		System.out.println(Math.min(Math.min(RGB_DP[N][Green], RGB_DP[N][Blue]), RGB_DP[N][Red]));
		
	}
}
