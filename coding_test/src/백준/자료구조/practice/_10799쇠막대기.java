package 백준.자료구조.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _10799쇠막대기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br    = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int cnt = 0;
		Stack<Character> stk = new Stack<>();
		boolean chk = true;
		
		for(int i = 0; i < str.length(); i++) {
			switch(str.charAt(i)) {
				case '(':
					chk = false;
					stk.add('(');
					break;
				case ')':
					stk.pop();
					if(chk) {
						cnt += 1;
					}else {
						cnt += stk.size();
						chk = true;
					}
					break;
			}			
		}
		
		System.out.println(cnt);
	}

}
