package 백준.자료구조.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 단어뒤집기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		Stack<Character> stk   = new Stack<>();
		Queue<Character> queue = new LinkedList<>();
		boolean chk_SpecialCharacter = false;
		
		for(int i = 0; i < str.length(); i++) {
			char character = str.charAt(i);
			switch(character) {
				case '<' :
					chk_SpecialCharacter = true;
					break;
				case '>' :
					chk_SpecialCharacter = false;
					queue.add(character);
					while(!stk.isEmpty()) {
						sb.append(stk.pop());
					}
					while(!queue.isEmpty()) {
						sb.append(queue.poll());
					}
					continue;
			}
			
			if(chk_SpecialCharacter) {
				queue.add(character);
			}else {
				switch(character) {
					case ' ' :
						while(!stk.isEmpty()) {
							sb.append(stk.pop());
						}
						sb.append(' ');
						break;
					default :
						stk.add(character);
						break;
				}
			}
		
		}
		
		while(!stk.isEmpty()) {
			sb.append(stk.pop());
		}
		
		System.out.println(sb.toString());
	}

}
