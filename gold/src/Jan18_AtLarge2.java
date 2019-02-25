import java.io.*;
import java.util.*;

public class Jan18_AtLarge2 {

  static class Node {
    public Node() {
      next = new ArrayList<>();
    }

    public ArrayList<Integer> next;
    public int parent;
    public int depth;
    public int minLeafDepth;
  }

  static int N, K;
  static Node[] tree;

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = "atlarge.in";
    String fout_name = "atlarge.out";
    if (ii > 0) {
      fin_name = String.format("data/atlarge_gold_jan18/%d.in", ii);
      fout_name = String.format("data/atlarge_gold_jan18/%d.out2", ii);
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
    // add edge to the tree
    for (int i = 1; i <= N-1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      tree[a].next.add(b);
      tree[b].next.add(a);
    }
    // fill in the parent, depth
    bfs(K);

    int ans = solve(K);
    pw.println(ans);
//    System.out.println(ans);
    pw.flush();
    pw.close();
  }

  static void bfs(int root) {
    tree[root].parent = -1;
    tree[root].depth = 0;

    LinkedList<Integer> q = new LinkedList<>();
    boolean added[] = new boolean[N+1];
    q.addLast(root);
    added[root] = true;

    ArrayList<Integer> bfs_order = new ArrayList<>();
    bfs_order.add(root);

    while(!q.isEmpty()) {
      int cur = q.removeFirst();
      for (int i : tree[cur].next) {
        if (!added[i]) {
          q.addLast(i);
//          q.addFirst(i);
          added[i] = true;
          bfs_order.add(i);
          tree[i].depth = tree[cur].depth + 1;
          tree[i].parent = cur;
        }
      }
    }
    for (int i = bfs_order.size() - 1; i >= 0; i--) {
      Node node = tree[bfs_order.get(i)];
      if (node.next.size() == 1 && node.parent != -1) {
        node.minLeafDepth = 0;
      } else {
        node.minLeafDepth = N;
        for (int kid : node.next) {
          if (kid == node.parent) continue;
          node.minLeafDepth = Math.min(node.minLeafDepth, tree[kid].minLeafDepth + 1);
        }
      }
    }
  }

  static int solve(int root) {
    LinkedList<Integer> q = new LinkedList<>();
    boolean added[] = new boolean[N+1];
    q.addLast(root);
    added[root] = true;
    int ans = 0;
    while (q.size() > 0) {
      Node node = tree[q.removeFirst()];
      if (node.depth < node.minLeafDepth) {
        for (int child : node.next) {
          if (child == node.parent) continue;
          q.addLast(child);
//          q.addFirst(child);
          added[child] = true;
        }
      } else {
        ans+=1;
      }
    }
    return ans;
  }
}
