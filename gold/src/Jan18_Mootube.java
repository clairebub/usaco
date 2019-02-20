import java.io.*;
import java.util.*;

public class Jan18_Mootube {

  // Union-find for graph node with index 1 to N
  static class UnionFind {
    int parents[];
    int sz[];

    public UnionFind(int n) {
      parents = new int[n+1];
      sz = new int[n+1];

      for (int i = 1; i <= n; i++) {
        parents[i] = i;
        sz[i] = 1;
      }
    }

    int find(int i) {
      return parents[i] == i ? i : (parents[i] = find(parents[i]));
    }

    void merge(int i, int j) {
      int fi = find(i);
      int fj = find(j);
      sz[fi] += sz[fj];
      parents[fj] = fi;
    }

    int sizeOf(int i) {
      return sz[find(i)];
    }
  }

  static class Edge implements Comparable<Edge> {
    int a, b, w;
    public Edge(int a, int b, int w) {
      this.a = a;
      this.b = b;
      this.w = w;
    }
    @Override
    public int compareTo(Edge o) {
      return this.w - o.w;
    }
  }

  static class Query implements Comparable<Query> {
    int k, v, i;

    public Query(int k, int v, int i) {
      this.k = k;
      this.v = v;
      this.i = i;
    }

    @Override
    public int compareTo(Query o) {
      return this.k - o.k;
    }
  }

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = String.format("data/mootube_gold_jan18/%d.in", ii);
    String fout_name = String.format("data/mootube_gold_jan18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "mootube.in";
      fout_name = "mootube.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int Q = Integer.parseInt(st.nextToken());
    UnionFind uf = new UnionFind(N);

    Edge[] edges = new Edge[N-1];
    for (int i = 0; i < N-1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      edges[i] = new Edge(a, b, w);
    }
    Arrays.sort(edges);

    Query[] queries = new Query[Q];
    for (int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine());
      int k = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      queries[i] = new Query(k, v, i);
    }
    Arrays.sort(queries);

    //
    int answers[] = new int[Q];
    int edge_idx = edges.length - 1;
    for (int q_idx = queries.length - 1; q_idx >= 0; q_idx--) {
      Query q = queries[q_idx];
      // merge all the edges who can survive this query
      while (edge_idx >= 0 && edges[edge_idx].w >= q.k) {
        uf.merge(edges[edge_idx].a, edges[edge_idx].b);
        edge_idx--;
      }
      answers[q.i] = uf.sizeOf(q.v) - 1;
    }
    for (int i = 0; i < Q; i++) {
      pw.println(answers[i]);
//      System.out.println(answers[i]);
    }
    pw.flush();
    pw.close();
  }
}
