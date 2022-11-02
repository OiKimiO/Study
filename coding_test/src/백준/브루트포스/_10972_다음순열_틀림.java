package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _10972_다음순열_틀림{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		int[] arr = new int[N];
		boolean bool = true;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = N-1; i >= 1; i--) {
			if(arr[i] > arr[i-1]){
				int length = i;
				
				int swap = arr[i-1];
				arr[i-1] = arr[i];
				arr[i] = swap;
				
				while(length != N) {
					queue.add(arr[length]);
					length++;
				}
				
				for(int k = i; k < N; k++) {
					arr[k] = queue.poll();
				}
				
				break;
			}
			if(i == 1) {
				bool = false;
			}
		}
		StringBuilder sb = new StringBuilder();
		if(bool) {
			for(int i = 0; i < N; i++) {
				sb.append(arr[i] + " ");
			}
		}else {
			sb.append("-1");
		}
		
		System.out.println(sb.toString());
	}

}
