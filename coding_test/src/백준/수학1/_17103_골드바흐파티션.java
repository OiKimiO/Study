package 백준.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _17103_골드바흐파티션 {

	public static void main(String[] args) throws IOException {
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
