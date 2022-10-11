package 백준.자료구조.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _17299오등큰수 {

	public static void main(String[] args) throws IOException {
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
