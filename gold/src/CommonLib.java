import java.util.*;

public class CommonLib {

  static class TreeNode {

  }

  // a Fenwick tree to handle the prefix sum for array of 'size'
  // For usage see Open18_Sort
  // That array is 0-based
  static class FenwickTree {
    int size;
    int[] array;

    public FenwickTree(int size) {
      this.size = size;
      array = new int[this.size + 1];  // the entry.0 is not used
      Arrays.fill(array, 0);
    }

    // index is inclusive
    public int prefix_sum(int index) {
      int sum = 0;
      while (index > 0) {
        sum += array[index];
        index -= index & (-index);
      }
      return sum;
    }

    public void increment(int index) {
      update(index, array[index] + 1);
    }

    public void update(int index, int v) {
      if (index <= 0) {
        throw new IllegalArgumentException("Illegal index: " + index);
      }
      while (index <= this.size) {
        array[index] += v;
        index += index & (-index);
      }
    }
  }

  static class MatchKey {
    int n;
    int[] flavors;

    public  MatchKey(int array_size) {
      n = 0;
      flavors = new int[array_size];
      Arrays.fill(flavors, 0);
    }

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

  // given a list of something, generating the subset systematically
  static MatchKey gen_subset(int[] flavors, int index) {
    MatchKey mk = new MatchKey(flavors.length);
    mk.n = 0;
    for (int i = 0; i < flavors.length; i++) {
      if (((1 << i) & index) != 0) {
        mk.flavors[mk.n] = flavors[i];
        mk.n++;
      }
    }
    return mk;
  }

  // doing network distance thing
  static void dijkstra(HashMap<Integer, HashMap<Integer, Integer>> edges, int src, HashMap<Integer, Integer> dist) {
    final int PQ_CAPACITY = 5000;
    PriorityQueue<Integer> front = new PriorityQueue<>(PQ_CAPACITY, new Comparator<Integer>() {
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

  // both w and t are 1-based array
  // n is last index
  // f(n, w):
  //    if this item doesn't fit the remaining capacity, f(n, w) = f (n-1, w),
  //    if this item is smaller than the remaining capacity, f(n, w) = max of v(n) + f(n-1, w-w(n)) And f(n-w, w)
  static int knapsack(int W, int[] w, int[] v, int n) {
    // base-case
    if (n == 0 || W <= 0) {
      return 0;
    }
    if (w[n] > W) {
      return knapsack(W, w, v, n-1);
    }

    int w1 = v[n] + knapsack(W-w[n], w, v, n-1);
    int w2 = knapsack(W, w, v, n-1);
    return Math.max(w1, w2);
  }

  // both w and v are 1-based array of weight and array, W is the total W
  // return the max V
  static int knapsack2(int W, int[] w, int[] v) {
    int n_items = w.length - 1;
    int[][] dp = new int[n_items+1][W+1];

    for (int j = 0; j <= W; j++) {
      dp[0][j] = 0;
    }
    for (int i = 0; i <= n_items; i++) {
      dp[i][0] = 0;
    }

    for (int i = 1; i <= n_items; i++) {
      for (int j = 1; j <= W; j++) {
        if(w[i] > j) {
          dp[i][j] = dp[i-1][j];
        } else {
          dp[i][j] = Math.max(dp[i-1][j], v[i] + dp[i-1][j-w[i]]);
        }
      }
    }
    return dp[n_items][W];
  }

  // w and t are 1-based, W is the minimum of sum of W, y is the minimum of sigma_t/sigma_w*1000
  static boolean knapsack_min_weight_max_ratio(int[] w, int[] t, int W, int y) {
    long dp[] = new long[W+1];
    for (int i = 0; i <= W; i++) {
      dp[i] = Integer.MIN_VALUE;
    }
    dp[0] = 0;
    for (int i = 1; i <= w.length-1; i++) {
      long value = 1000 * (long) t[i] - y * (long) w[i]; // if you choose i, how much will it impact the ratio calc
      int inc = w[i]; // increase this much of weight
      for (int k = W; k >= 0; k--) { // reverse order, so when you are adding w[i], you are looking at all the dp[k]
                                     // which is not impacted by the new weight yet, with this new weight, it's going to
                                     // update k2, which is going to be bigger than the k unless it's the limit
        int k2 = Math.min(W, k+inc); // if you already have total weight K.
        if (dp[k] != Integer.MIN_VALUE) { // if we already know the ratio for total weight of K
          if (dp[k2] < dp[k] + value) { // we can calcluate the k+inc ratio
            dp[k2] = dp[k] + value;
          }
        }
      }
    }
    return dp[W] > 0;
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
