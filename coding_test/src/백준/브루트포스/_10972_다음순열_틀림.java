package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _10972_다음순열_틀림{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		int[] arr = new int[N];
		boolean bool = false;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		outer:for(int i = N-1; i >= 1; i--) {
			int prev_i = i-1;
			if(arr[i] > arr[prev_i]){
				
				for(int last_i = N-1; last_i >= i; last_i--) {
					if(arr[prev_i] < arr[last_i]) {
						int swap    = arr[prev_i];
						arr[prev_i] = arr[last_i];
						arr[last_i] = swap;
						
						int k = N - 1;
						
						while(i < k) {
							int tmp = arr[i];
							arr[i] = arr[k];
							arr[k] = tmp;
							i++;
							k--;
						}
						bool = true;
						break outer;
					}
				}
				
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		if(bool) {
			for(int i = 0; i < N; i++) {
				sb.append(arr[i] + " ");
			}
			sb.substring(0, sb.length()-1);
		}else {
			sb.append("-1");
		}
		
		System.out.println(sb.toString());
	}

}
