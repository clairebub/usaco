import java.io.*;
import java.util.*;

public class Dining3 {

  static int N, M, K;
  static HashMap<Integer, HashMap<Integer, Integer>> edges;
  static HashMap<Integer, Integer> hays;

  public static void main(String[] args) throws Exception {
/*
    for (int i = 1; i <= 10; i++) {
      long t1 = System.currentTimeMillis();
      run(i);
      long t2 = System.currentTimeMillis();
      System.out.println("i=" + i + ", ts=" + (t2-t1) + " ms");
    }
    */
    run(0);

  }

  static void run(int ii)throws Exception {
    String fin_name = String.format("data/dining_gold_dec18/%d.in", ii);
    String fout_name = String.format("data/dining_gold_dec18/s_out_%d.out", ii);
    if (ii == 0) {
      fin_name = "dining.in";
      fout_name = "dining.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    edges = new HashMap<>();
    hays = new HashMap<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      if (!edges.containsKey(a)) {
        edges.put(a, new HashMap<>());
      }
      if (!edges.containsKey(b)) {
        edges.put(b, new HashMap<>());
      }
      edges.get(a).put(b, w);
      edges.get(b).put(a, w);
    }
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int p = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      hays.put(p, y);
    }

    // get the distance between barn and each field
    HashMap<Integer, Integer> dist = new HashMap<>();
    dist.put(N, 0);
    dijkstra(N, dist);

    HashMap<Integer, Integer> dist2 = new HashMap<>();

    edges.put(N+1, new HashMap<>());
    for (int f : hays.keySet()) {
      int w = dist.get(f) - hays.get(f);
      edges.get(N+1).put(f, w);
    }
    dist2.put(N+1, 0);
    dijkstra(N+1, dist2);

    for (int i = 1; i <= N-1; i++) {
      int v = dist2.get(i) <= dist.get(i) ? 1 : 0;
      pw.println(v);
    }
    pw.flush();
    pw.close();
  }

  static void dijkstra(int src, HashMap<Integer, Integer> dist) {
    PriorityQueue<Integer> front = new PriorityQueue<>(N, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return dist.get(o1) - dist.get(o2);
      }
    });

    front.add(src);
    while (!front.isEmpty()) {
      int a = front.poll();
      for (int b : edges.get(a).keySet()) {
        if (!dist.containsKey(b) || dist.get(b) > dist.get(a) + edges.get(a).get(b)) {
          dist.put(b, dist.get(a) + edges.get(a).get(b));
          front.offer(b);
        }
      }
    }
  }

  static void dijkstra2(int src, HashMap<Integer, Integer> dist) {
    LinkedList<Integer> front = new LinkedList<>();
    front.add(src);
    while (!front.isEmpty()) {
      int a = front.removeFirst();
      for (int b : edges.get(a).keySet()) {
        if (!dist.containsKey(b) || dist.get(b) > dist.get(a) + edges.get(a).get(b)) {
          dist.put(b, dist.get(a) + edges.get(a).get(b));
          front.addLast(b);
        }
      }
    }
  }
}
