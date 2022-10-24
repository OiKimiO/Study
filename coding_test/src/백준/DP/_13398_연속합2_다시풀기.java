package 백준.DP;

import java.io.*;
import java.util.*;

public class _13398_연속합2_다시풀기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp1 = new int[N];
		int[] dp2 = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		// 왼쪽에서의 연속합을 구했을 경우
		dp1[0] = arr[0];
		for(int i = 1; i < N; i++) {
			if(dp1[i-1] + arr[i] < arr[i]) {
				dp1[i] = arr[i];
			}else {
				dp1[i] = dp1[i-1] + arr[i];
			}
			
			max = Math.max(dp1[i],max);
		}
		
		// 오른쪽에서 연속합을 구했을 경우
		
		// i값을 제외한 왼쪽과 오른쪽의 연속합을 더했을 경우
		
		
		System.out.println(max);
		
	}
}
