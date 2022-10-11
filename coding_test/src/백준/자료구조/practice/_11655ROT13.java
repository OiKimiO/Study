package 백준.자료구조.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11655ROT13 {

	public static void main(String[] args) throws IOException {
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
