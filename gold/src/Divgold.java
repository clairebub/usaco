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

        //
        // another way for the solution, this time bottom up
        //
        // A hash map where key is the value of a pile, the value is number
        // of ways to achieve it. We only need to get up to sum/2, because it's symmetrical to the other
        // pile.
        LinkedList<HashMap<Integer, Integer>> all_ways = new LinkedList<>();
        HashMap<Integer, Integer> ways = new HashMap<>();
        ways.put(0, 1);
        ways.put(gold[0], 1);
        all_ways.addLast(ways);
        for (int i = 1; i < N; i++) {
            HashMap<Integer, Integer> current_ways = new HashMap<>();
            HashMap<Integer, Integer> prev_ways = all_ways.getLast();
            for (int k : prev_ways.keySet()) {
                int v1 = k;
                if (!current_ways.containsKey(v1)) {
                    current_ways.put(v1, 0);
                }
                current_ways.put(v1, current_ways.get(v1) + prev_ways.get(k));
                int v2 = k + gold[i];
                if (!current_ways.containsKey(v2)) {
                    current_ways.put(v2, 0);
                }
                current_ways.put(v2, current_ways.get(v2) + prev_ways.get(k));
            }
            all_ways.addLast(current_ways);
        }
        int i = sum/2;
        while (true) {
            if (all_ways.getLast().containsKey(i)) {
                System.out.println((sum - i) - i);
                System.out.println(all_ways.getLast().get(i));
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
