import java.io.*;
import java.util.*;

public class MilkOrder {

    static int N, M;
    static LinkedList<Integer> succ[]; // successor nodes of the current node
    static int pred[]; // number of predecessors of the current node
    static LinkedList<Integer> observations[];
    static int result[];

    public static void main(String[] args) throws Exception {
        long start_time = System.currentTimeMillis();
        Scanner fin = new Scanner(new File("milkorder_gold_open18/7.in"));
        N = fin.nextInt();
        succ = new LinkedList[N+1];
        pred = new int[N+1];
        for (int i = 0; i <= N; i++) {
            succ[i] = new LinkedList<>();
            pred[i] = 0;
        }
        result = new int[N];
        M = fin.nextInt();
        observations = new LinkedList[M+1];
        for (int i = 1; i <= M; i++) {
            observations[i] = new LinkedList<>();
            int l = fin.nextInt();
            for (int j = 0; j < l; j++) {
                observations[i].add(fin.nextInt());
            }
            System.out.println("Observations: " + observations[i]);
        }
        // binary search to find the largest k that's doable
        int lo = 1;
        int hi = M;
        while (hi > lo + 1) {
            int mid = (lo + hi) / 2;
            if (top_sortable(mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (top_sortable(lo+1)) {
            lo = lo + 1;
        }
        int k = lo;
        System.out.println("k=" + k);
        System.out.println("time: " + (System.currentTimeMillis() - start_time) + " ms");
    }

    // is the first K'th observation sortable?
    static boolean top_sortable(int k) {
        for (int i = 0; i <= N; i++) {
            succ[i] = new LinkedList<>();
            pred[i] = 0;
        }
        for (int i = 1; i <= M; i++) {
            int prev = -1;
            for (int node : observations[i]) {
                if (prev == -1) {
                    prev = node;
                    continue;
                }
                succ[prev].add(node);
                pred[node]++;
                prev = node;
            }
        }
        // it cannot be topologically sorted if every node has a pred
        boolean found_a_start = false;
        for (int i = 1; i <= N; i++) {
            if (pred[i] == 0) {
                found_a_start = true;
            }
        }
        // it's not top-sortable if it cannot find a start.
        if (!found_a_start) {
            return false;
        }
        // put the sortable results into an array of N
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (pred[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {

        }
        return true;
    }
}
