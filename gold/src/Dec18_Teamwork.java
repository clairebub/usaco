import java.io.*;
import java.util.*;

public class Dec18_Teamwork {

  static int N, K;
  static int[] skills;
  static int[] dp;

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = String.format("data/teamwork_gold_dec18/%d.in", ii);
    String fout_name = String.format("data/teamwork_gold_dec18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "teamwork.in";
      fout_name = "teamwork.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    skills = new int[N+1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      skills[i] = Integer.parseInt(st.nextToken());
    }

    dp = new int[N+1];
    Arrays.fill(dp, 0);
    int max = 0;
    for (int i = 1; i <= K; i++) {
      max = Math.max(max, skills[i]);
      dp[i] = max * i;
    }
    for (int i = K+1; i <= N; i++) {
      max = skills[i];
      int sum = max + dp[i-1];
      for (int j = 1; j <= K-1; j++) {
        max = Math.max(max, skills[i-j]);
        sum = Math.max(sum, dp[i-1-j] + (j+1) * max);
      }
      dp[i] = sum;
    }
    pw.println(dp[N]);
    pw.flush();
    pw.close();
  }

}
