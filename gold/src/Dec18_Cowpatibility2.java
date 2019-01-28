import java.io.*;
import java.util.*;

public class Dec18_Cowpatibility2 {
  static int N;
  static HashMap<MatchKey, Integer> compat = new HashMap<>();

  static class MatchKey {
    public  MatchKey() {
      n = 0;
      Arrays.fill(flavors, 0);
    }

    int n;
    int[] flavors = new int[5];

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof MatchKey)) {
        return false;
      }
      MatchKey mk = (MatchKey) obj;
      if (mk.n != n) {
        return false;
      }
      for (int i = 0; i < n; i++) {
        if (flavors[i] != mk.flavors[i]) {
          return false;
        }
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 17;
      result = 37 * result + n;
      for (int i = 0; i < n; i++) {
        result = 37 * result + flavors[i];
      }
      return result;
    }
  }

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
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 5; j++) {
        flavors[j] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(flavors);
      for (int j = 1; j <= 31; j++) {
        MatchKey mk = gen_subset(flavors, j);
        int count = compat.containsKey(mk) ? compat.get(mk) : 0;
        compat.put(mk, ++count);
      }
    }

    // get number of compatible pairs using inclusion-exclusion
    long[] inc_ex = {0, 1, -1, 1, -1, 1};
    long total = 1L * N * (N-1) / 2;
    for (Map.Entry<MatchKey, Integer> entry : compat.entrySet()) {
      long matched_people = entry.getValue();
      if (matched_people == 1) {
        continue;
      }
      long matched_pairs = matched_people * (matched_people - 1) / 2;
      total -= inc_ex[entry.getKey().n] * matched_pairs;
    }

    pw.println(total);
    pw.flush();
    pw.close();
  }


  static MatchKey gen_subset(int[] flavors, int index) {
    MatchKey mk = new MatchKey();
    mk.n = 0;
    for (int i = 0; i < 5; i++) {
      if (((1 << i) & index) != 0) {
        mk.flavors[mk.n] = flavors[i];
        mk.n++;
      }
    }
    return mk;
  }
}
