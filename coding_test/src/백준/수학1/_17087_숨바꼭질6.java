package 백준.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17087_숨바꼭질6 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int gcd_num = 0;
		int brother_position1 = Integer.parseInt(st.nextToken());
		int distance1 = Math.abs(S-brother_position1);
		
		for(int i = 0; i < N-1; i++) {
			int brother_position2 = Integer.parseInt(st.nextToken());
			int distance2         = Math.abs(S-brother_position2);
			gcd_num = gcd(distance1,distance2);
			distance1 = gcd_num;
		}
		
		if(N == 1) {
			System.out.println(distance1);
		}else {
			System.out.println(gcd_num);
		}
	}

	private static int gcd(int distance1, int distance2) {
		if(distance2 == 0) {
			return distance1;
		}else {
			return gcd(distance2, distance1%distance2);
		}
	}

}
