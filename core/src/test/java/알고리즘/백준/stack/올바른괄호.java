package 알고리즘.백준.stack;

import java.util.Stack;

import org.junit.jupiter.api.Test;

public class 올바른괄호 {

	@Test
	void calculate() {
		String str = "((())(";
		int cnt = 0;
		for(int i = 0; i < str.length(); i++) {
			char data = str.charAt(i);
			if(data == '(') {
				cnt++;
			}else {
				cnt--;
			}
			if(cnt < 0) {
				System.out.println("잘못된 괄호");
				break;
			}
		}
		
		if(cnt > 0) {
			System.out.println("잘못된 괄호");
		}else if(cnt == 0){
			System.out.println("옳바른 괄호");
		}
	}
}
