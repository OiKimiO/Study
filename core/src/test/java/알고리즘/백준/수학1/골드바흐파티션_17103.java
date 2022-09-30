package 알고리즘.백준.수학1;

import java.io.*;

import org.junit.jupiter.api.Test;

public class 골드바흐파티션_17103 {

	@Test
	void main() throws NumberFormatException, IOException {
		int max = 1000001;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] chk_prime = new boolean[max+1];
		chk_prime[0] = chk_prime[1] = true;
		
		for(int i = 2; i <= max; i++) {
			if(!chk_prime[i]) {
				for( int j = i*2; j <=max; j+=i ) {
					chk_prime[j] = true;
				}
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0) {
			int N   = Integer.parseInt(br.readLine());
			int cnt = 0;
			for(int i = 2; i <= N/2; i++) {
				if(!chk_prime[i]) {
					if(!chk_prime[N-i]) {
						cnt++;
					}
				}
			}
			sb.append(cnt+"\n");
			T--;
		}
		
		System.out.print(sb.toString());
	}
}
