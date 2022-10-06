package 알고리즘.백준.queue_stack_practice;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class _17299오등큰수 {

	@Test
	void main() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] NumCount = new int[n];
		int[] arr      = new int[n];
		int[] answer   = new int[n];
		Stack<Integer> stk = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			NumCount[num]++;
			arr[i] = num;
		}
		
		for(int i = 0; i < n; i++) {
			while(!stk.isEmpty() && NumCount[arr[stk.peek()]] < NumCount[arr[i]]) {
				answer[stk.pop()] = arr[i];
			}
			stk.add(i);
		}
		
		while(!stk.isEmpty()) {
			answer[stk.pop()] = -1;
		}
		
		for(int i = 0; i < n; i++) {
			sb.append(answer[i] + " ");
		}
		
		System.out.print(sb.toString());
		
	}
}
