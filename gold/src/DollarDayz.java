import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class DollarDayz {

    static int N, K;
    static BigInteger[][] answers;
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("dollardayz.in"));
        N = fin.nextInt();
        K = fin.nextInt();
        answers = new BigInteger[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                answers[i][j] = null;
            }
        }
        nk(N, K);
        System.out.println(answers[N][K]);
    }

    static BigInteger nk(int n, int k) {
        if (k == 1) {
            answers[n][k] = BigInteger.ONE;
            return answers[n][k];
        }
        if (answers[n][k] != null) {
            return answers[n][k];
        }
        BigInteger total = BigInteger.ZERO;
        // buying k dollar-ed item i times
        for (int i = 0; n >= i*k; i++) {
            total = total.add(nk(n-i*k, k-1));
        }
        answers[n][k] = total;
        return answers[n][k];
    }
}
