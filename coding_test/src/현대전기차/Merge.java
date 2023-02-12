package 현대전기차;

/* 아래의 도시리스트 2개를 합치고 중복값을 제거한후 출력하도록 하세요.*/

import java.util.HashSet;
import java.util.Arrays;

public class Merge{
    
    public static String[] uniqNames(String[] names1, String[] names2) {
        /*
         * 답안 작성
         * 배열 합치기
         * 중복제거
         */
    	HashSet<String> set = new HashSet<String>();
    	for(int i = 0; i < names1.length; i++) {
        	set.add(names1[i].trim());
        }
    	
        for(int i = 0; i < names2.length; i++) {
        	set.add(names2[i].trim());
        }
    	
        String[] answer = set.toArray(new String[0]);
        
        return answer;
    }
    
    public static void main(String[] args) {
        String[] names1 = new String[] {"Seoul ", "Incheon", "Gyeonggi"};
        String[] names2 = new String[] {"Seoul", "Cheonan", "Busan"};
        /*
         * 답안 작성
         */
        
        String[] answer = uniqNames(names1,names2);
        int lastIdx = answer.length-1;
        for(int i = 0; i < answer.length; i++) {
        	if(i == lastIdx) {
        		System.out.print(answer[i]);
        	}else {
        		System.out.print(answer[i]+", ");
        	}
        }
        
// should print Seoul, Incheon, Gyeonggi, Cheonan, Busan
    }
}
