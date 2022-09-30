package 알고리즘.백준.queue_stack_practice;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class 오큰수_17298_다시풀기 {

	@Test
	void main() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Integer> stk = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int[] arr_number = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr_number[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2N
		for(int i = 0; i < N; i++) {
			while(!stk.isEmpty() && arr_number[stk.peek()] < arr_number[i]) {
				arr_number[stk.pop()] = arr_number[i];
			}
			stk.add(i);
		}
		
		// N
		while(!stk.isEmpty()) {
			arr_number[stk.pop()] = -1;
		}
		
		// N
		for(int i = 0; i < N; i++) {
			sb.append(arr_number[i] + " ");
		}
		
		System.out.println(sb.toString());
	}
}
