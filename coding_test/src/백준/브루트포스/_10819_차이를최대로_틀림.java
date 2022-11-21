package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _10819_차이를최대로_틀림 {
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int max = 0;
		while(nextPermutation()) {
			int cal = calcurate();
			if(max < cal) max = cal;
		}
		
		System.out.println(max);
	}
	
	
	
	private static int calcurate() {
		int answer = 0;
		for(int i = 0; i < arr.length-1; i++) {
			answer += Math.abs(arr[i] - arr[i+1]);
		}
		return answer;
	}



	private static boolean nextPermutation() {
		int switch_idx = 0;
		for(int current_idx = arr.length-1; current_idx >= 1; current_idx--) {
			int current_number = arr[current_idx];
			int prev_idx       = current_idx-1;
			int prev_number    = arr[prev_idx];
			if(current_number  > prev_number) {
				for(switch_idx = arr.length-1; switch_idx > prev_idx; switch_idx--) {
					int switch_number = arr[switch_idx];
					if(prev_number < switch_number) {
						int tmp             = arr[switch_idx];
						    arr[switch_idx] = arr[prev_idx];
						    arr[prev_idx]   = tmp;
					    break;
					}
				}
				
				switch_idx = arr.length-1;
				
				while(switch_idx > current_idx) {
					int tmp                 = arr[switch_idx];
						arr[switch_idx]     = arr[current_idx];
						arr[current_idx] = tmp;
					switch_idx--;
					current_idx++;
				}
				
				return true;
			}
		}
		return false;
	}
	
}
