package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _15988_123더하기3 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Long[] arr = new Long[1000001];
		StringBuilder sb = new StringBuilder();		
		arr[1] = 1L;
		arr[2] = 2L;
		arr[3] = 4L;
		for(int i = 4; i < arr.length; i++) {
			arr[i] = (arr[i-1] + arr[i-2] + arr[i-3]) % 1000000009; 
		}
		
		for(int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(arr[n]).append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
}
