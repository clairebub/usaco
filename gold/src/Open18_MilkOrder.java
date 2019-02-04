import java.io.*;
import java.util.*;

public class Open18_MilkOrder  {

  static int N;
  static int M;

  public static void main(String[]args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = String.format("data/milkorder_gold_open18/%d.in", ii);
    String fout_name = String.format("data/milkorder_gold_open18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "milkorder.in";
      fout_name = "milkorder.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    HashMap<Integer, HashSet<Integer>>[] edges = new HashMap[M];
    for (int i = 0; i < M; i++) {
      edges[i] = new HashMap<>();
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int last = -1;
      for (int j = 0; j < x; j++) {
        int curr = Integer.parseInt(st.nextToken());
        if (j == 0) {
          last = curr;
          continue;
        } else {
          if (!edges[i].containsKey(last)) {
            edges[i].put(last, new HashSet<>());
          }
          edges[i].get(last).add(curr);
          last = curr;
        }
      }
    }
    // invariant: lo <= X < hi
    int lo = 0;
    int hi = M;

    int[] answer = new int[N];
    while (hi > lo + 1) {
      int mid = (lo + hi) / 2;
      if(topological_sort(answer, N, edges, mid)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    topological_sort(answer, N, edges, lo);
    for (int i = 0; i < answer.length; i++) {
      if (i != 0) {
        pw.print(" ");
//        System.out.print(" ");
      }
      pw.print(answer[i]);
//      System.out.print(answer[i]);
    }
    pw.println();
//    System.out.println();
    pw.flush();
    pw.flush();
  }

  // only take the first x of the array, inclusive
  static boolean topological_sort(int[] answer, int N, HashMap<Integer, HashSet<Integer>>[] edges, int x) {
    int ind = 0;

    // from the edges, for each node, record number of predecessors because when it's zero, this node
    // is good to go, also keeps a list of its successors, because when the pred is gone, the successor's
    // predecessor count goes down by 1
    int[] pred_counts = new int[N+1];
    ArrayList<Integer>[] succ = new ArrayList[N+1];
    for (int i = 1; i <= N; i++) {
      pred_counts[i] = 0;
      succ[i] = new ArrayList<>();
    }

    for (int i = 0; i <= x; i++) {
      for (int a : edges[i].keySet()) {
        for (int b : edges[i].get(a)) {
          pred_counts[b]++;
          succ[a].add(b);
        }
      }
    }
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for(int i = 1; i <= N; i++) {
      if (pred_counts[i] == 0) {
        pq.offer(i);
      }
    }
    while (!pq.isEmpty()) {
      int v = pq.poll();
      answer[ind++] = v;
      for (int p : succ[v]) {
        pred_counts[p]--;
        if(pred_counts[p] == 0) {
          pq.offer(p);
        }
      }
    }
    return ind == N;
  }
}
