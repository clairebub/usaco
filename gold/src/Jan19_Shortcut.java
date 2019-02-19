import java.io.*;
import java.util.*;

public class Jan19_Shortcut {

  static int N, M, T;
  static int COWS[];
  static int TOTAL_COWS = 0;

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = String.format("data/shortcut_gold_jan19/%d.in", ii);
    String fout_name = String.format("data/shortcut_gold_jan19/%d.out2", ii);
    if (ii == 0) {
      fin_name = "shortcut.in";
      fout_name = "shortcut.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());
    COWS = new int[N+1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      COWS[i] = Integer.parseInt(st.nextToken());
      TOTAL_COWS += COWS[i];
    }
    HashMap<Integer, HashMap<Integer, Integer>> edges = new HashMap<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      if (!edges.containsKey(a)) {
        edges.put(a, new HashMap<>());
      }
      edges.get(a).put(b, t);
      if (!edges.containsKey(b)) {
        edges.put(b, new HashMap<>());
      }
      edges.get(b).put(a, t);
    }

    HashMap<Integer, Integer> dist = new HashMap<>();
    dist.put(1, 0);
    HashMap<Integer, Integer> backtrack = dijkstra(edges, 1, dist);
    HashMap<Integer, HashSet<Integer>> shortest_dist_tree = new HashMap<>();
    for (Map.Entry<Integer, Integer> entry : backtrack.entrySet()) {
      int child = entry.getKey();
      int parent = entry.getValue();
      if (!shortest_dist_tree.containsKey(parent)) {
        shortest_dist_tree.put(parent, new HashSet<>());
      }
      shortest_dist_tree.get(parent).add(child);
    }
//    System.out.println("shortest distance treee: " + shortest_dist_tree);
//    int size_subtree1[] = new int[N+1];
//    dfs(shortest_dist_tree, 1, size_subtree1);
    int size_subtree2[] = new int[N+1];
    dfs_without_recursion(shortest_dist_tree, 1, size_subtree2);
//    System.out.println(Arrays.toString(size_subtree));
    long answer = 0;
    for (int i = 1; i <= N; i++) {
      answer = Math.max(answer, 1L * size_subtree2[i] * (dist.get(i) - T));
    }
    pw.println(answer);
    pw.flush();
    pw.close();
    System.out.println(answer);
  }

  static void dfs_without_recursion(HashMap<Integer, HashSet<Integer>> tree, int root, int[] size_subtree) {
    Stack<Integer> stack = new Stack<>();
    stack.push(root);
    boolean visited[] = new boolean[N+1];
    for (int i = 1; i <= N; i++) {
      visited[i] = false;
    }
    while (!stack.empty()) {
      int top = stack.peek();
      if (!visited[top]) {
        // visit
        if (tree.containsKey(top)) {
          for (int child : tree.get(top)) {
            stack.push(child);
          }
        } else {
          stack.pop();
          size_subtree[top] += COWS[top];
        }
        visited[top] = true;
      } else {
        stack.pop();
        if (tree.containsKey(top)) {
          for (int child : tree.get(top)) {
            size_subtree[top] += size_subtree[child];
          }
          size_subtree[top] += COWS[top];
        } else {
          size_subtree[top] += COWS[top];
        }
      }
    }
  }

  static void dfs(HashMap<Integer, HashSet<Integer>> tree, int root, int[] size_subtree) {
    size_subtree[root] += COWS[root];
    if (tree.containsKey(root)) {
      for (int child : tree.get(root)) {
        dfs(tree, child, size_subtree);
        size_subtree[root] += size_subtree[child];
      }
    }
  }

  // doing network distance thing
  static HashMap<Integer, Integer> dijkstra(HashMap<Integer, HashMap<Integer, Integer>> edges, int src, HashMap<Integer, Integer> dist) {
    final int PQ_CAPACITY = 5000;
    PriorityQueue<Integer> front = new PriorityQueue<>(PQ_CAPACITY, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return dist.get(o1) - dist.get(o2);
      }
    });

    HashMap<Integer, Integer> backtrack = new HashMap<>();

    front.add(src);
    while (!front.isEmpty()) {
      int a = front.poll();
      for (int b : edges.get(a).keySet()) {
        if (!dist.containsKey(b) || dist.get(b) > dist.get(a) + edges.get(a).get(b)) {
          dist.put(b, dist.get(a) + edges.get(a).get(b));
          backtrack.put(b, a);
          front.offer(b);
        }
      }
    }
    return backtrack;
  }
}
