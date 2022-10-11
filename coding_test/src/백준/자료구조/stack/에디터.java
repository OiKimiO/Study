package 백준.자료구조.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 에디터 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Character> left = new Stack<>(),right = new Stack<>();
		
		for(int i = 0; i < str.length(); i++) {
			left.push(str.charAt(i));
		}
		
		while(n > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch(st.nextToken().charAt(0)) {
				case 'L' :
					if(!left.isEmpty()) {
						right.add(left.pop());
					}
					break;
				case 'D' :
					if(!right.isEmpty()) {
						left.add(right.pop());
					}
					break;
				case 'B' :
					if(!left.isEmpty()) {
						left.pop();
					}
					break;
				case 'P' :
					left.add(st.nextToken().charAt(0));
					break;
			}
			
			n--;
		}
		
		while(!left.isEmpty()) {
			right.add(left.pop());
		}
		
		while(!right.isEmpty()) {
			sb.append(right.pop());
		}
		System.out.println(sb.toString());
	}

}
