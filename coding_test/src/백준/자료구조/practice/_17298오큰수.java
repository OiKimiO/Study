package 백준.자료구조.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _17298오큰수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int n              = Integer.parseInt(br.readLine());
		int[] arr          = new int[n];
		int[] answer       = new int[n];
		StringBuilder sb   = new StringBuilder();
		Stack<Integer> stk = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		for(int i = 0; i < n; i++) {
			while(!stk.isEmpty() && arr[stk.peek()] < arr[i]) {
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
		
		System.out.println(sb.toString());
	}

}
