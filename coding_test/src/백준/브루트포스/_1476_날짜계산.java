package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _1476_날짜계산 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int IE = 1;
		int IS = 1;
		int IM = 1;
		int answer = 1;
		
		while(true) {
			if(IE == E && IS == S && IM == M) {
				break;
			}else {
				answer++;
				IE++;
				IS++;
				IM++;
				
				if(IE > 15) {
					IE = 1;
				}
				
				if(IS > 28) {
					IS = 1;
				}
				
				if(IM > 19) {
					IM = 1;
				}
			}
		}
		
		System.out.println(answer);
	}

}
