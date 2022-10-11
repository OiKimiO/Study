package 백준.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1676_팩토리얼0의개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;	
		for(int i = 5; i <= n; i += 5) {
			int num = i;
			while(num % 5 == 0) {
				num /= 5;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
