package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10973_이전순열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[N];
		boolean check = false;
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		outter : for(int i = N-1; i >= 1; i--) {
			if(arr[i] < arr[i-1]) {
				for(int j = N-1; j >= i; j--) {
					if(arr[i-1] > arr[j]) {
						int swap = arr[i-1];
						arr[i-1] = arr[j];
						arr[j]   = swap;
						
						int k = N-1;
						
						while(i < k) {
							swap   = arr[i];
							arr[i] = arr[k];
							arr[k] = swap;
							i++;
							k--;
						}
						check = true;
						break outter;
					}
				}
			}
		}
		
		if(check) {
			for(int i = 0; i < N; i++) {
				sb.append(arr[i] + " ");
			}
		}else {
			sb.append("-1");
		}
		
		System.out.println(sb.toString());
		
	}	
}
