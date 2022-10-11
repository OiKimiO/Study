package 백준.자료구조.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1158요세푸스 {

	public static void main(String[] args) throws IOException {
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
