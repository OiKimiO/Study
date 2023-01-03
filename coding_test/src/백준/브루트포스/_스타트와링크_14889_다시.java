package 백준.브루트포스;

import java.io.*;
import java.util.*;

// 맞는데 너무 느림... ㅠㅜ
public class _스타트와링크_14889_다시 {
	static boolean[] visited;
	static int[] ATeam;
	static int ATeamSum;
	static boolean[][] check_bool;
	static int[] BTeam;
	static int BTeamSum;
	static int[][] arr_stat;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N    = Integer.parseInt(br.readLine());
		int TeamCount = N/2;
		ATeam = new int[TeamCount];
		BTeam = new int[TeamCount];
		arr_stat = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				arr_stat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(N,0,TeamCount, 0);
		
		System.out.println(answer);
	}

	// 모든 팀이 될 수 있는 경우의 수를 구한다.
	private static void dfs(int N, int start, int TeamCount, int cost) {
		if(TeamCount == cost) {
			compare(N,TeamCount);
			return;
		}
		
		for(int i = start; i < N; i++) {
			if(visited[i]) {
				continue;
			}
			
			visited[i] = true;
			dfs(N,i+1,TeamCount,cost+1);
			visited[i] = false;
		}
	}

	// ATeam과 BTeam의 스탯 차를 구한다.
	private static void compare(int N,int TeamCount) {
		selectTeam(N);
		ATeamSum = 0;
		BTeamSum = 0;
		
		check_bool = new boolean[TeamCount][TeamCount];
		ASumStat(0,TeamCount);
		
		check_bool = new boolean[TeamCount][TeamCount];
		BSumStat(0,TeamCount);
		
		int margin   = Math.abs(ATeamSum-BTeamSum);
		
		if(answer > margin) {
			answer = margin;
		}
	}

	// 팀원들의 스탯 합을 구한다.
	private static void ASumStat(int start, int TeamCount) {
		if(start == TeamCount) {
			return;
		}
		
		for(int end = 0; end < TeamCount; end++) {
			int teamStartIdx = ATeam[start];
			int teamEndIdx   = ATeam[end];
			int stat         = arr_stat[teamStartIdx][teamEndIdx];
			
			if(check_bool[start][end]) {
				continue;
			}
			
			if(stat == 0) {
				continue;
			}
			
			check_bool[start][end] = true;
			
			ATeamSum += stat;
			ASumStat(start+1, TeamCount); 
		}	
	}

	private static void BSumStat(int start, int TeamCount) {
		if(start == TeamCount) {
			return;
		}
		
		for(int end = 0; end < TeamCount; end++) {
			int teamStartIdx = BTeam[start];
			int teamEndIdx   = BTeam[end];
			int stat         = arr_stat[teamStartIdx][teamEndIdx];
			
			if(check_bool[start][end]) {
				continue;
			}
			
			if(stat == 0) {
				continue;
			}
			
			check_bool[start][end] = true;
			
			BTeamSum += stat;
			BSumStat(start+1, TeamCount); 
		}	
	}
	
	// 현재 설정된 팀으로 구한다.
	private static void selectTeam(int N) {
		int Aidx = 0;
		int Bidx = 0;
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) {
				ATeam[Aidx] = i;
				Aidx++;
			}else {
				BTeam[Bidx] = i;
				Bidx++;
			}
		}
	}
}
