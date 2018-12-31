import java.io.*;
import java.util.*;

public class Divgold {
    static int N;
    static int[] gold;
    static int sum;
    static int[][] answer;

    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("divgold.in"));
        N = fin.nextInt();
        gold = new int[N];
        for(int i = 0; i < N; i++){
            gold[i] = fin.nextInt();
            sum += gold[i];
        }
        answer = new int[N+1][sum+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(answer[i], -1);
        }
        // best answer is to make a pile at sum/2, the next best is bigger by one, then by two
        // you can stop if you get a good one. we use dp and memoization to avoid re-computating
        // ways to get a pile of coins with a given value using the first i coins
        for (int i = sum / 2; i <= sum; i++) {
            int w = find_ways(N-1, i);
            if (w > 0) {
                System.out.println(Math.abs((sum - i) - i));
                System.out.println(w);
                break;
            }
        }
    }

    // number of ways to get a subset of value j using the elements [0, i]
    static int find_ways(int i, int j) {
        if (answer[i][j] >= 0) {
            return answer[i][j];
        }
        if (i == 0) {
            int w;
            if (j == gold[i]) {
                w = 1;
            } else {
                w = 0;
            }
            answer[i][j] = w;
            return answer[i][j];
        }
        int w = find_ways(i-1, j);
        if (j > gold[i]) {
            w += find_ways(i-1, j - gold[i]);
        }
        answer[i][j] = w;
        return answer[i][j];
    }
/*
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("divgold.in"));
        N = fin.nextInt();
        gold = new int[N];
        for(int i = 0; i < N; i++){
            gold[i] = fin.nextInt();
            sum += gold[i];
        }
        answer = new int[sum+1];
        good = new boolean[sum+1];
        for (int i = 0; i <= sum; i++) {
            answer[i] = 0;
            good[i] = false;
        }
        answer[0] = 1;
        good[0] = true; good[sum] = true;
        for (int i = 0; i < N; i++) {
            int v = gold[i];
            for (int j = sum / 2; j >= v; j--) {
                answer[j] = (answer[j] + answer[j - v]) % 1000000;
                if (good[j - v]) good[j] = true;
            }
        }

        int best = sum / 2;
        for (best = sum / 2; !good[best]; best--) {}

        System.out.println(Math.abs(best - (sum - best)));
        System.out.println(answer[best]);
    } */
}
