package 알고리즘.백준.stack;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class 올바른괄호 {

	@Test
	void calculate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(n > 0) {
			String str = br.readLine();
			int cnt = 0;
			for(int i = 0; i < str.length(); i++) {
				char data = str.charAt(i);
				if(data == '(') {
					cnt++;
				}else {
					cnt--;
				}
				if(cnt < 0) {
					sb.append("NO").append("\n");
					break;
				}
			}
			
			if(cnt > 0) {
				sb.append("NO").append("\n");
			}else if(cnt == 0){
				sb.append("YES").append("\n");
			}
			n--;
		}
		
		System.out.println(sb.toString());
	}
}
