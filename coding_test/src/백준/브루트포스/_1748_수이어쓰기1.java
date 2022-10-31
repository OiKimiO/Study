package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1748_수이어쓰기1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int length = 0;
		for(int i = 1; i <= N; i++) {
			int num = i;
			while(num / 10 >= 1) {
				length++;
				num /= 10;
			}
			length++;
		}
		System.out.println(length);
	}

}
