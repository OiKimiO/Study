package 알고리즘.백준.queue_stack_practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class _10808알파벳개수 {
	@Test
	void main() throws IOException{
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[26];
		
		for(int i = 0; i < str.length(); i++) {
			arr[str.charAt(i)-97]++;
		}
		
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + " ");
		}
		
		System.out.println(sb.toString());
		
	}
}
