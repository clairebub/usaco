import java.io.*;
import java.util.*;

public class Jan18_Atlarge {

  static int N, K;
  static ArrayList<Integer> edges[];

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = "atlarge.in";
    String fout_name = "atlarge.out";
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    edges = new ArrayList[N+1];
    HashMap<Integer, Integer> count = new HashMap();

    for (int i = 1; i <= N-1; i++) {
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      edges[a].add(b);
      edges[b].add(a);
      if (!count.containsKey(a)) {
        count.put(a, 1);
      } else {
        count.put(a, count.get(a) + 1);
      }

      if (!count.containsKey(b)) {
        count.put(b, 1);
      } else {
        count.put(b, count.get(b) + 1);
      }
    }

    ArrayList<Integer> leaves = new ArrayList<>();
    for (Integer b : count.keySet()) {
      if (count.get(b) == 1) {
        leaves.add(b);
      }
    }

    // Got the leaves and the root
    // get the distance from root

  }

  static void dfs_for_distance(int root, int[] dist) {


  }
}
