package 백준.브루트포스;

import java.util.*;
import java.io.*;

public class _15661_링크와스타트_틀림 {
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= N; i++) {
			combination(0,0,N,i);
		}
		
		System.out.println(answer);
	}
	
	// 각 팀에 어떤 사람들을 배치할지 정하는 것
	private static void combination(int start, int cost, int N, int total) {
		/* total 1인 경우
		 *   - 0      1     2     3    4
		 *    true  false false false false
		 *   링크팀  1명 
		 *   스타트팀 4명 
		 *   으로 팀역량을 구하는 거임
		 */
		if(total == cost) {
			diff(N);
			return;
		}
		
		for(int i = start; i < N; i++) {
			if(visited[i]) {
				continue;
			}
			
			visited[i] = true;
			combination(0,cost+1,N, total);
			visited[i] = false;
		}
	}
	
	// 팀 합계간의 차이를 구함
	private static void diff(int N) {
		int RinkTeamSum  = 0;
		int StartTeamSum = 0;
		
		for(int i = 0; i<N-1; i++) {
			for(int j = i+1; j<N; j++) {
				if(visited[i] && visited[j]) {
					RinkTeamSum  += (map[i][j] + map[j][i]);
				}
				
				if(!visited[i] && !visited[j]) {
					StartTeamSum += (map[i][j] + map[j][i]);
				}
			}
		}
		
		int margin = Math.abs(RinkTeamSum - StartTeamSum);
		
		if(answer > margin) {
			answer = margin;
		}
	}

}
