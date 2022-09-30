package 알고리즘.백준.수학1;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class GCD합_9613 {
	
	@Test
	void main() throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Integer[] list = null;
		while(T > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N   = Integer.parseInt(st.nextToken());
			Long sum = 0L;
			list = new Integer[N];
			
			for(int i = 0; i < N; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
					sum += gcd(list[i],list[j]);
				}
			}
			
			sb.append(sum+"\n");
			
			T--;
		}
		System.out.print(sb.toString());
	}

	private Integer gcd(Integer num1, Integer num2) {
		if(num2 == 0) {
			return num1;
		}else {
			return gcd(num2, num1%num2);
		}
	}
}
