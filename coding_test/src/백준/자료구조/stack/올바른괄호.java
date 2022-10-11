package 백준.자료구조.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 올바른괄호 {

	public static void main(String[] args) throws IOException {
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
