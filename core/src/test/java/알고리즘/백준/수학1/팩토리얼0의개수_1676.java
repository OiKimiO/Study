package 알고리즘.백준.수학1;

import java.io.*;

import org.junit.jupiter.api.Test;

public class 팩토리얼0의개수_1676 {

	@Test
	void main() throws NumberFormatException, IOException {
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
