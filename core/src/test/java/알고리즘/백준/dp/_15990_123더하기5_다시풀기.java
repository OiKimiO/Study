package 알고리즘.백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class _15990_123더하기5_다시풀기 {
	
	@Test
	void main() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// int T     = Integer.parseInt(br.readLine());
		int mod   = 1000000009;
		int[][] d = new int[11][4];
		
		for(int i = 1; i <= 10; i++) {
			if(i-1 >= 0) {
				int num1 = d[i][1];
				int num2 = d[i-1][2] + d[i-1][3];
				d[i][1] = Math.max(num1, num2);
				
				if(i == 1) {
					d[1][1] = 1;
				}
			}
			
			if(i-2 >= 0) {
				int num1 = d[i][2];
				int num2 = d[i-2][1] + d[i-2][3];
				d[i][2] = Math.max(num1, num2);
				if(i == 2) {
					d[2][2] = 1;
				}
			}
			
			if(i-3 >= 0) {
				int num1 = d[i][3];
				int num2 = d[i-3][2] + d[i-3][3];
				d[i][3] = Math.max(num1,num2);
				if(i == 3) {
					d[3][3] = 1;
				}
			}
		}
		
		
	}
}
