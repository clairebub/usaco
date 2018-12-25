import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * N <= 50,000
 * M <= 100,000
 * K <= N
 */
public class Dining {

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

    static int N, M, K;

    static HashMap<Integer, Integer> hayVals = new HashMap<>();
    static HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<>();
    static HashMap<Pair, Integer> trails = new HashMap<>();

    public static void main(String[] args) throws Exception {
        long start_time = System.currentTimeMillis();
        Scanner fin = new Scanner(new File("dining_gold_dec18/10.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Scanner fin2 = new Scanner(new File("dining_gold_dec18/10.out"));
        ArrayList<Integer> answers = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            answers.add(fin2.nextInt());
        }
        // init the graph with two hashmaps: one is a lookup from a node to a list of neighbors, another
        // is a pair to the edge weight
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(fin.nextLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            trails.put(new Pair(a, b), cost);
            trails.put(new Pair(b, a), cost);
            HashSet n = neighbors.containsKey(a) ? neighbors.get(a) : new HashSet();
            n.add(b);
            neighbors.put(a, n);
            n = neighbors.containsKey(b) ? neighbors.get(b) : new HashSet();
            n.add(a);
            neighbors.put(b, n);
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(fin.nextLine());
            int h = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            hayVals.put(h, v);
        }
//        System.out.println("hayVals: " + hayVals);
 //       System.out.println("neighbors: " + neighbors);
 //       System.out.println("trails: " + trails);
        System.out.println("time: " + (System.currentTimeMillis() - start_time));
        HashMap<Integer, Integer> dist = dijkstra2(N);
        System.out.println("time 2: " + (System.currentTimeMillis() - start_time));
//        System.out.println("dist from barn to pasture: " + dist);

        // modify the graph to make the traversal start at H, with an initial distance
        neighbors.put(N+1, new HashSet<>());
        for (Integer p : hayVals.keySet()) {
            neighbors.get(N+1).add(p);
            Pair edge = new Pair(N+1, p);
            trails.put(edge, dist.get(p) - hayVals.get(p));
        }
        // now it's like traversing the graph from each H, the distance marked on each node would
        // be the distance from the node to the barn, keeping the minimum. If that distance is getting
        // smaller, it means it cost less to visit the barn going through the barn.
        HashMap<Integer, Integer> dist2 = dijkstra2(N+1);


        PrintWriter fout = new PrintWriter(new File("dining.out"));
        // PrintWriter fout = new PrintWriter(System.out);
        for (int i = 1; i < N; i++) {
            int v = dist2.get(i) <= dist.get(i) ? 1 : 0;
            fout.println(v);
            if (v != answers.get(i-1)) {
                System.out.println("****** wrong at " + i);
                System.exit(1);
            }
        }
        fout.close();
        System.out.println("time 3: " + (System.currentTimeMillis() - start_time));
    }

    /**
     * THIS HAS A BUG. USE THE dijkstra2() function.
     * Dijkstra algorith, given a source, mark each visited node with the minimum distance reachable.
     * Return a hashmap of distances

    static HashMap<Integer, Integer> dijkstra(int source) {
        // distance from source to the given node
        HashMap<Integer, Integer> dist = new HashMap<>();
        dist.put(source, 0);

        ArrayList<Integer> toBeVisited = new ArrayList<>();
        toBeVisited.add(source);

        HashSet<Integer> visited = new HashSet<>();

        while(!toBeVisited.isEmpty()) {
            int visiting = toBeVisited.remove(0);
            HashSet<Integer> n_list = neighbors.get(visiting);
            for (int t : n_list) {
                if (visited.contains(t)) {
                    continue;
                }
                Pair edge = new Pair(visiting, t);
                if (!dist.containsKey(t) || dist.get(t) > dist.get(visiting) + trails.get(edge)) {
                    dist.put(t, dist.get(visiting) + trails.get(edge));
                }
                toBeVisited.add(t);
            }
            visited.add(visiting);
        }
        return dist;
    }
    */

    static HashMap<Integer, Integer> dijkstra2(int source) {
        HashMap<Integer, Integer> dist = new HashMap<>();
        dist.put(source, 0);

        LinkedList<Pair> visited = new LinkedList<>();
        visited.add(new Pair(-1, source));
        while(!visited.isEmpty()) {
            Pair edge = visited.remove();
            int b = edge.b; // the node just arrived
            for (int t : neighbors.get(b)) {
                Pair e2 = new Pair(b, t);
                if (!dist.containsKey(t) || dist.get(t) > dist.get(b) + trails.get(e2)) {
                    dist.put(t, dist.get(b) + trails.get(e2));
                    visited.add(e2);
                }
            }
        }
        return dist;
    }
}
