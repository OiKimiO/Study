package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _9095_123더하기 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[12];
		StringBuilder sb = new StringBuilder();		
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		for(int i = 4; i < 12; i++) {
			arr[i] = arr[i-1]+arr[i-2]+arr[i-3];
		}
		
		while(n > 0) {
			int idx = Integer.parseInt(br.readLine());
			sb.append(arr[idx]).append('\n');
			n--;
		}
		
		System.out.println(sb.toString());
	}
	
}
