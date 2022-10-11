package 백준.자료구조.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 단어뒤집기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		while(n > 0) {
			String str = br.readLine();
			Stack<Character> stk = new Stack<>();
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < str.length(); i++) {
				if(str.charAt(i) != ' ') {
					stk.add(str.charAt(i));
				}else {
					while(!stk.isEmpty()) {
						sb.append(stk.pop());
					}
					
					sb.append(' ');
				}
			}
			
			while(!stk.isEmpty()) {
				sb.append(stk.pop());
			}
			
			System.out.println(sb.toString());
			n--;
		}
	}

}
