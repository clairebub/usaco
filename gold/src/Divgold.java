import java.io.*;
import java.util.*;

public class Divgold {
    static int N;
    static int[] V;
    static int[][] dp;
    static int min_diff = Integer.MAX_VALUE;
    static int min_diff_count = 0;
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("divgold.in"));
        N = fin.nextInt();
        V = new int[N];
        for(int i = 0; i < N; i++){
            V[i] = fin.nextInt();
        }
        //System.out.println(Arrays.toString(V));
        System.out.println(div_gold(0, 0));
    }

    static int div_gold(int i, int diff) {
        if (i == N - 1) {
            int diff_1 = Math.abs(diff + V[i]);
            int diff_2 = Math.abs(diff - V[i]);
            return Math.min(diff_1, diff_2);
        }
        int diff_1 = Math.abs(div_gold(i + 1, diff + V[i]));
        int diff_2 = Math.abs(div_gold(i + 1, diff - V[i]));
        return Math.min(diff_1, diff_2);
    }
}
