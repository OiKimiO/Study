package 백준.브루트포스;

import java.io.*;
import java.util.*;

class _14501_퇴사_틀림{
    static int[] t;
    static int[] p;
    static int n;
    static int ans = 0;
    static void go(int day, int sum) {
        if (day == n+1) {
            if (ans < sum) ans = sum;
            return;
        }
        
        if (day > n+1) {
            return;
        }
        
        // 당일날 계산하지 않고 다음날로 넘어감
        go(day+1, sum);
        
        // 당일날 계산을 함
        go(day+t[day], sum+p[day]);
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        t = new int[n+1];
        p = new int[n+1];
        for (int i=1; i<=n; i++) {
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }
        go(1, 0);
        System.out.println(ans);
    }

}