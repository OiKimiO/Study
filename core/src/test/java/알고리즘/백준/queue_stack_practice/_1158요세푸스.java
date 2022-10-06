package 알고리즘.백준.queue_stack_practice;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class _1158요세푸스 {
	
	@Test
	void main() throws IOException{
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder   sb = new StringBuilder();
						sb.append("<");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= n; i++) {
			queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			for(int i = 0; i < k-1; i++) {
				queue.add(queue.poll());
			}
			if(queue.size() == 1) {
				sb.append(queue.poll());
			} else {
				sb.append(queue.poll() + ", ");
			}
		}
		
		sb.append(">");
		
		System.out.print(sb.toString());
	}

}
