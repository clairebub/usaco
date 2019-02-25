import java.io.*;
import java.util.*;

public class Dec17_BarnPainting {
  static int N, K;
  static Node[] tree;
  final static int COLOR_CHOICES = 3;
  static ArrayList<Integer> bfs_order;
  final static long MOD = 1000L * 1000L * 1000L + 7L;

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = "barnpainting.in";
    String fout_name = "barnpainting.out";
    if (ii > 0) {
      fin_name = String.format("data/barnpainting_gold_dec17/%d.in", ii);
      fout_name = String.format("data/barnpainting_gold_dec17/%d.out2", ii);
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    tree = new Node[N+1];
    for (int i = 1; i <= N; i++) {
      tree[i] = new Node();
    }
    for (int i = 1; i <= N-1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      tree[a].next.add(b);
      tree[b].next.add(a);
    }
    for (int i = 1; i <= K; i++) {
      st = new StringTokenizer(br.readLine());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      tree[b].color = c;
    }
    long ans = solve();
//    System.out.println(ans);
    pw.println(ans);
    pw.flush();
    pw.close();
  }

  static long solve() {
    for (int i = 1; i <= N; i++) {
      int c1 = tree[i].color;
      for (int j : tree[i].next) {
        int c2 = tree[j].color;
        if (c1 > 0 && c2 > 0 && c1 == c2) {
          return 0L;
        }
      }
    }
    bfs(1);
    long dp[][] = new long[N+1][4];
    for (int i = bfs_order.size() - 1; i >= 0; i--) {
      int v = bfs_order.get(i); // solve for this node
      Node node = tree[v];

      for (int j = 1; j <= 3; j++) {
        // node painted in this color of j
        if (node.color > 0 && node.color != j) continue; // node has been painted with a different color already

        long res = 1L; // result for this node painted in color j
        for (int child : node.next) {
          if (child == node.parent) continue;
          long child_not_in_j = dp[child][0] - dp[child][j];
          res *= (dp[child][0] - dp[child][j] + MOD) % MOD;
          res %= MOD;
        }
        dp[v][j] = res;
      }
      for (int j = 1; j <= 3; j++) {
        dp[v][0] += dp[v][j];
        dp[v][0] = dp[v][0] % MOD;
      }
    }
    return dp[1][0];
  }

  static void bfs(int root) {
    LinkedList<Integer> q = new LinkedList<>();
    q.addFirst(root);
    boolean[] added = new boolean[N+1];
    added[root] = true;

    bfs_order = new ArrayList<>();
    bfs_order.add(root);

    while(q.size() > 0) {
      int curr = q.removeFirst();
      for (int child : tree[curr].next) {
        if (child == tree[curr].parent) continue;
        if (added[child]) continue;
        q.addLast(child);
        added[child] = true;
        bfs_order.add(child);
        tree[child].parent = curr;
      }
    }
  }

  static class Node {
    public Node() {
      color = 0;
      parent = 0;
    }

    int color;
    int parent;
    ArrayList<Integer> next = new ArrayList<>();

  }
}
