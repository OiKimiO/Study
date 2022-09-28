package 알고리즘.백준.stack;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class 단어뒤집기 {

	@Test
	void main() throws IOException {
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
