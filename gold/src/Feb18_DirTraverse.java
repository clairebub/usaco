import java.io.*;
import java.util.*;

public class Feb18_DirTraverse {

  static int N;
  static int n_leaves = 0;

  static class Node {
    public Node(String name, boolean is_file, int n_children) {
      this.name = name;
      this.is_file = is_file;
      this.children = new int[n_children];

      //
      this.name_len = name.length();
      this.num_leaves = 0;
      this.total_within = 0;
      this.total = 0;
    }
    String name;
    boolean is_file;
    int[] children;

    long num_leaves = 0; // number of leaves in this tree
    long name_len;
    long total_within;
    long total;
  }

  static Node[] nodes;

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii)throws Exception {
    String fin_name = String.format("data/dirtraverse_gold_feb18/%d.in", ii);
    String fout_name = String.format("data/dirtraverse_gold_feb18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "dirtraverse.in";
      fout_name = "dirtraverse.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    nodes = new Node[N+1];

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      String name = st.nextToken();
      int m = Integer.parseInt(st.nextToken());
      if (m == 0) {
        n_leaves ++;
      }
      nodes[i] = new Node(name, m == 0 ? true : false, m);
      for (int j = 0; j < m; j++) {
        nodes[i].children[j] = Integer.parseInt(st.nextToken());
      }
    }
    dfs(1);
    dfs2(1, 0);
    long answer = -1;
    for (int i = 1; i <= N; i++) {
      Node node = nodes[i];
      if (!node.is_file) {
        if (answer < 0 || answer > node.total) {
          answer = node.total;
        }
      }
    }
//    System.out.println(answer);
    pw.println(answer);
    pw.flush();
    pw.close();

  }

  // set the number of leaves in this tree and name lens in it's subtrees (excluding the current one)
  static void dfs(int node_index) {
    Node node = nodes[node_index];
    if (node.is_file) {
      node.num_leaves = 1;
      node.total_within = 0;
    } else {
      for (int i = 0; i < node.children.length; i++) {
        dfs(node.children[i]);
        Node child = nodes[node.children[i]];
        if (child.is_file) {
          node.num_leaves += 1;
          node.total_within += child.name_len;
        } else {
          node.num_leaves += child.num_leaves;
          node.total_within += child.total_within + child.num_leaves * (child.name_len + 1);
        }
      }
    }
  }

  static void dfs2(int node_index, long total_outside) {
    Node node = nodes[node_index];
    if (node.is_file) {
      return;
    }
    node.total = total_outside + node.total_within;

    for (int i = 0; i < node.children.length; i++) {
      int child_index = node.children[i];
      Node child = nodes[child_index];
      long total_in_sibling = node.total_within - (child.total_within + child.num_leaves * (child.name_len + 1));
      long total_outside_child = total_outside + total_in_sibling + 3 * (n_leaves - child.num_leaves);
      dfs2(child_index, total_outside_child);
    }
  }
}
