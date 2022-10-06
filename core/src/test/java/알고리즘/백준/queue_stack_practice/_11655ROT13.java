package 알고리즘.백준.queue_stack_practice;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class _11655ROT13 {

	@Test
	void main() throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
				int asciiChar = str.charAt(i) + 13;
				if( asciiChar > 'z') {
					int margin   = (asciiChar - 'z')-1;
					char newChar = (char) ('a' + margin);
					sb.append(newChar);
				}else {
					char newChar = (char) (asciiChar);
					sb.append(asciiChar);
				}
			}else if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
				int asciiChar = str.charAt(i) + 13;
				if( asciiChar > 'Z') {
					int margin   = (asciiChar - 'Z')-1;
					char newChar = (char) ('A' + margin);
					sb.append(newChar);
				}else {
					sb.append(asciiChar);
				}
			}else {
				sb.append(str.charAt(i));
			}
		}
		
		System.out.println(sb.toString());
	}
}
