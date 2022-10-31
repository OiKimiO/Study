package 백준.브루트포스;

import java.io.*;
import java.util.*;

public class _2309_일곱난쟁이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[9];
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 9;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		outter : for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				int sum = 0;
				if(i != j) {
					
					for(int k = 0; k < 9; k++) {
						if(k == i || k == j) continue;
						sum += arr[k]; 
					}
					
					if(sum == 100) {
						for(int k = 0; k < 9; k++) {
							if(k == i || k == j) continue;
							sb.append(arr[k]).append('\n');
						}
						break outter;
					}
					
				}
			}
		}
		
		System.out.println(sb.toString());
	}

}
