package CNS_Code;

import java.util.*;
import java.io.*;

public class _1 {

	static boolean[] check;
	static String answer = "";
	static int prevLength = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(),",");
		int[] arr = new int[4];
		check = new boolean[4];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		dfs(arr, "",0);
		
		System.out.println(answer);
	}

	private static void dfs(int[] arr, String str ,int depth) {
		if(depth == arr.length) return;
		for(int i = 0; i < arr.length; i++) {
			if(check[i]) continue;
			check[i] = true;
			if(fn_CheckMarble(str+arr[i])) {
				prevLength = answer.length();
				String string2 = str+arr[i];
				if(prevLength <= 1) {
					answer = string2;
				}else if(string2.length() > 1){
					
					if(string2.length() > prevLength) {
						answer = string2;
					}else if(string2.length() % 2 == 1 && string2.length() == prevLength) {
						int middle = prevLength/2;
						if(answer.substring(0, middle-1).equals(string2.substring(0, middle-1))
						   && answer.substring(middle+1, prevLength-1).equals(string2.substring(middle+1, prevLength-1))) {
							answer = string2;
						}
					}
				}
			}
			
			dfs(arr, str+arr[i], depth+1);
			check[i] = false;
		}
	}

	private static boolean fn_CheckMarble(String string) {
		int length = string.length();
		// 자기자신만 있는 경우
		if(length == 1) {
			return true;
		// 홀수
		}else if(length % 2 == 1) {
			int middle = length/2;
			int LeftSum  = 0;
			int RightSum = 0;
			
			for(int i = middle-1; i >=0; i-- ) {
				LeftSum += (string.charAt(i) - '0');
			}
			
			for(int i = middle+1; i < length; i++ ) {
				RightSum += (string.charAt(i) - '0');
			}
			
			if(LeftSum == RightSum) {
				return true;
			}else {
				return false;
			}
		// 짝수
		}else if(length %2 == 0) {
			int middle   = length/2;
			int LeftSum  = 0;
			int RightSum = 0;
			
			for(int i = middle-1; i >=0; i-- ) {
				LeftSum += (string.charAt(i) - '0');
			}
			
			for(int i = middle; i < length; i++ ) {
				RightSum += (string.charAt(i) - '0');
			}
			
			if(LeftSum == RightSum) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

}
