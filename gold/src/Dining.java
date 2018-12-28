import java.io.*;
import java.util.*;

/**
 * N <= 50,000
 * M <= 100,000
 * K <= N
 */
public class Dining {

    static class PointDistance {
        final int p, d;
        PointDistance(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }

    static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof  Pair)) {
                return false;
            }
            Pair that = (Pair) obj;
            return this.a == that.a && this.b == that.b;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 37 * result + a;
            result = 37 * result + b;
            return result;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", a, b);
        }
    }

    public static void main(String[] args) throws Exception {
        int N, M, K;
        HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<>();
        HashMap<Pair, Integer> edges = new HashMap<>();
        HashMap<Integer, Integer> hays = new HashMap<>();

        Scanner fin = new Scanner(new File("dining_gold_dec18/2.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // Read in the graph, which is represented by two hash maps: one is given a node, get a
        // set of its neighbors; the other is given an edge, get its weight.
        for (int i = 1; i <= N; i++) {
            neighbors.put(i, new HashSet<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(fin.nextLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.put(new Pair(a, b), w);
            edges.put(new Pair(b, a), w);
            neighbors.get(a).add(b);
            neighbors.get(b).add(a);
        }
        // Read in the location of hays and their values
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(fin.nextLine());
            int h = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            hays.put(h, v);
        }
        // Get the optimal distance from barn to each pasture
        HashMap<Integer, Integer> dist_from_barn = new HashMap<Integer, Integer>();
        dist_from_barn.put(N, 0);
        dijkstra(Arrays.asList(N), dist_from_barn, neighbors, edges);

        // Optimal distance from barn to each pasture with one stop at the hays can be calculated
        // as a sum of two segments: the first segment is the optimal distance from the barn to the
        // hays; the second segment is the optimal distance from the hays to each pasture. It makes
        // sense for cow to stop at the hays if the sum of these two segments minus the hay value is
        // smaller than the optimal distance from barn to pasture calculated earlier.
        // The way we calculate the sum of these two segments is as following: first use dijkstra to
        // calculate the distance from barn to hays, then minus the hay's yumminess value. Then use the
        // pastures with hays as the sources and the (distance - yumminess value) as their respective
        // starting distance to calculate the distance to each pasture. These distance is the distance
        // the cow would have travelled if it has first find the best path to go to a hay, eat it and
        // then go to the barn, because the optimal distance of cow going from individual pasture to hay
        // and then barn is the same as cow going from barn to hay and going to each pasture.
        HashMap<Integer, Integer> dist_through_hay = new HashMap<>();
        for (Integer h : hays.keySet()) {
            dist_through_hay.put(h, dist_from_barn.get(h) - hays.get(h));
        }
        dijkstra(new ArrayList(hays.keySet()), dist_through_hay, neighbors, edges);

        PrintWriter fout = new PrintWriter(new File("dining.out"));
        // PrintWriter fout = new PrintWriter(System.out);
        for (int i = 1; i < N; i++) {
            int v = dist_through_hay.get(i) <= dist_from_barn.get(i) ? 1 : 0;
            fout.println(v);
            System.out.println(v);
        }
        fout.close();
    }

    // visit from the given sources, update reachable distance to dist.
    // each source already has its distance in the dist parameter
    static void dijkstra(
            List<Integer> sources,
            HashMap<Integer, Integer> dist,
            HashMap<Integer, HashSet<Integer>> neighbors,
            HashMap<Pair, Integer> edges) {

        LinkedList<Integer> visited = new LinkedList<>();
        /*
        PriorityQueue<Integer> visited = new PriorityQueue<>(neighbors.size(), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist.get(o1) - dist.get(o2);
            }
        }); */
        visited.addAll(sources);
        while (!visited.isEmpty()) {
            int v = visited.poll();
            for (Integer t : neighbors.get(v)) {
                Pair e = new Pair(v, t);
                int w = edges.get(e);
                if (!dist.containsKey(t) || dist.get(t) > dist.get(v) + w) {
                    dist.put(t, dist.get(v) + w);
                    visited.offer(t);
                }
            }
        }
    }
}
