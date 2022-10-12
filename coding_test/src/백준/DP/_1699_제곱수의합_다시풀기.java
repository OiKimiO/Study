package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1699_제곱수의합_다시풀기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N    = Integer.parseInt(br.readLine());
		int sqrt = (int) Math.sqrt(N);
		int answer = 0;
		
		while(N > 0) {
			if(N < 4) {
				answer += N;
				break;
			}
			if(N > sqrt*sqrt) {
				answer++;
				N -= (sqrt*sqrt);
			}else {
				while(N < sqrt*sqrt) {
					sqrt--;
				}
				answer++;
				N -= (sqrt*sqrt);
			}
		}
		
		System.out.println(answer);
	}

}
