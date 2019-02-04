import java.io.*;
import java.util.*;

public class Open18_Talent {

  final static int WMAX = 1000;
  final static int NMAX = 250;
  static int N;
  static int W;
  static int weights[];
  static int talents[];
  // The dp state
  // For 0 <=i < w, this is the maximum score achievable with weight exactly i
  // For i == w, this is the maximum talent with weight AT LEAST w
  static long dp[] = new long[WMAX+1];
  static long dp2[][];

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }
  
  static void run(int ii) throws Exception {
    String fin_name = String.format("data/talent_gold_open18/%d.in", ii);
    String fout_name = String.format("data/talent_gold_open18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "talent.in";
      fout_name = "talent.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());

    weights = new int[N+1];
    talents = new int[N+1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      weights[i] = Integer.parseInt(st.nextToken());
      talents[i] = Integer.parseInt(st.nextToken());
    }

    // Binary search
    // Invariant lo <= answer < hi
    int lo = 1;
    int hi = 1000*1000;
    while (hi > lo + 1) {
      int mid = (hi + lo) / 2;
      if (doable(weights, talents, W, mid)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    System.out.println(lo);
    pw.println(lo);
    pw.flush();
    pw.close();

  }

  // w and t are 1-based, W is the minimum of sum of W, y is the minimum of sigma_t/sigma_w*1000
  static boolean doable(int[] w, int[] t, int W, int y) {
    long dp[] = new long[WMAX+1];
    for (int i = 0; i <= WMAX; i++) {
      dp[i] = Integer.MIN_VALUE;
    }
    dp[0] = 0;
    for (int i = 1; i <= N; i++) {
      long value = 1000 * (long) t[i] - y * (long) w[i]; // if you choose i, how much will it impact the ratio calc
      int inc = w[i]; // increase this much of weight
      for (int k = W; k >= 0; k--) {
        int k2 = Math.min(W, k+inc); // if you already have total weight K.
        if (dp[k] != Integer.MIN_VALUE) { // if we already know the ratio for total weight of K
          if (dp[k2] < dp[k] + value) { // we can calcluate the k+inc ratio
            dp[k2] = dp[k] + value;
          }
        }
      }
    }
    return dp[W] > 0;
  }
}
