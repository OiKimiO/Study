package 알고리즘.백준.queue_stack_practice;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class 쇠막대기_10799 {

	@Test
	void main() throws IOException {
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
