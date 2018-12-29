import java.io.*;
import java.util.*;

public class DollarDayz {

    static int N, K;
    static long[][] answers;
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("dollardayz.in"));
        N = fin.nextInt();
        K = fin.nextInt();
        answers = new long[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                answers[i][j] = -1;
            }
        }
        nk(N, K);
        System.out.println(answers[N][K]);
    }

    static long nk(int n, int k) {
        if (k == 1) {
            answers[n][k] = 1;
            return 1;
        }
        if (answers[n][k] >= 0) {
            return answers[n][k];
        }
        long total = 0;
        // buying k dollar-ed item i times
        for (int i = 0; n >= i*k; i++) {
            total += nk(n-i*k, k-1);
        }
        answers[n][k] = total;
        return answers[n][k];
    }
}
