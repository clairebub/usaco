import java.io.*;
import java.util.*;

public class Dec18_Cowpatibility2 {
  static int N;
  static HashMap<String, Integer> compat = new HashMap<>();
  static int[] index2Keysize = new int[32];

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = String.format("data/cowpatibility_gold_dec18/%d.in", ii);
    String fout_name = String.format("data/cowpatibility_gold_dec18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "cowpatibility.in";
      fout_name = "cowpatibility.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int[] flavors = new int[5];
    int[] taken = new int[5];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 5; j++) {
        flavors[j] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(flavors);
      for (int j = 1; j <= 31; j++) {
        String k = gen_subset(flavors, taken, j);
        if (!compat.containsKey(k)) {
          compat.put(k, 1);
        } else {
          compat.put(k, compat.get(k) + 1);
        }
      }
    }

    // get number of compatible pairs using inclusion-exclusion
    long[] inc_ex = {0, 1, -1, 1, -1, 1};
    long total = 1L * N * (N-1) / 2;
    for (Map.Entry<String, Integer> entry : compat.entrySet()) {
      long matched_people = entry.getValue();
      if (matched_people == 1) {
        continue;
      }
      long matched_pairs = matched_people * (matched_people - 1) / 2;
      st = new StringTokenizer(entry.getKey(), ",");
      int n = Integer.parseInt(st.nextToken());
      total -= inc_ex[n] * matched_pairs;
    }
    pw.println(total);
    pw.flush();
    pw.close();
  }


  static void gen_index(int index) {
    int n = 0;
    for (int i = 0; i < 5; i++) {
      if (((1 << i) & index) != 0) {
        n++;
      }
    }
    index2Keysize[index] = n;
  }

  // pivot, subscribe the cow into each flavor set it might find its compatible mate in.
  static String gen_subset(int[] flavors, int[] taken, int index) {
    int n = 0;
    for (int i = 0; i < 5; i++) {
      if (((1 << i) & index) != 0) {
        taken[n] = flavors[i];
        n++;
      }
    }

    StringBuilder sb = new StringBuilder("" + n);
    for (int i = 0; i < n; i++) {
      sb.append(",");
      sb.append(taken[i]);
    }
    return sb.toString();
  }
}
