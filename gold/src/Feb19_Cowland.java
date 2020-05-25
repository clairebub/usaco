import java.io.*;
import java.util.*;

public class Feb19_Cowland {

  static int N, Q;
  static Node[] NODES;
  static int dp[][];

  public static void main(String[] args) throws Exception {
    int x = 1 ^ 4 ^ 16;
    BufferedReader br = new BufferedReader(new FileReader("cowland.in"));
    PrintWriter pw = new PrintWriter(new File("cowland.out"));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    Q = Integer.parseInt(st.nextToken());
    NODES = new Node[N+1];

    for (int i = 1; i <= N; i++) {
      NODES[i] = new Node();
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      NODES[i].e_value = Integer.parseInt(st.nextToken());
    }
    for (int i = 1; i <= N-1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      NODES[a].next.add(b);
      NODES[b].next.add(a);
    }

    for (int q = 1; q <= Q; q++) {
      st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      if (command == 1) {
        int i = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        NODES[i].e_value = v;
      }
      if (command == 2) {
        int i = Integer.parseInt(st.nextToken());
        int j = Integer.parseInt(st.nextToken());
        int ans = solve(i, j);
//        System.out.println(ans);
        pw.println(ans);
      }
    }

    pw.flush();
    pw.close();
  }

  static int solve(int from, int to) {
    LinkedList<Integer> q = new LinkedList<>();
    boolean added[] = new boolean[N+1];

    q.addLast(from);
    added[from] = true;

    while (q.size() > 0) {
      int curr = q.removeFirst();
      Node node = NODES[curr];
      for (int next : node.next) {
        if (!added[next]) {
          q.addLast(next);
          added[next] = true;
          NODES[next].parent = curr;
        }
      }
      if (curr == to) {
        break;
      }
    }

    int curr = to;
    int res = NODES[curr].e_value;
    while (curr != from) {
      curr = NODES[curr].parent;
      res ^= NODES[curr].e_value;
    }
  //  res ^= NODES[from].e_value;
    return res;
  }

  static class Node {
    public Node() {}

    int parent;
    int e_value;
    ArrayList<Integer> next = new ArrayList<>();
  }
}
