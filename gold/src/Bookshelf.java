import java.io.*;
import java.util.*;

public class Bookshelf {

    static int N, L;
    static int h[];
    static int w[];
    static int dp[][];

    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("bookshelf.in"));
        N = fin.nextInt();
        L = fin.nextInt();
        h = new int[N];
        w = new int[N];
        dp = new int[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            h[i] = fin.nextInt();
            w[i] = fin.nextInt();
        }
        System.out.println(min_height(0, N-1));
    }

    static int min_height(int i_start, int i_end) {
        if (i_start > i_end) {
            return 0;
        }
        if (i_start == i_end) {
            return h[i_start];
        }

        if (dp[i_start][i_end] > 0) {
            return dp[i_start][i_end];
        }
        int answer = Integer.MAX_VALUE;
        int shelf_width = 0;
        int shelf_height = 0;
        for (int i = i_start; i <= i_end; i++) {
            shelf_width += w[i];
            if (shelf_width > L) {
                break;
            }
            shelf_height = Math.max(shelf_height, h[i]);
            int answer_i = shelf_height + min_height(i + 1, i_end);
            answer = Math.min(answer, answer_i);
        }
        //System.out.println("i_start=" + i_start + ", i_end=" + i_end + ", answer=" + answer);
        dp[i_start][i_end] = answer;
        return answer;

    }
}
