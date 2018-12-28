
import java.io.*;
import java.util.*;

/**
 * N <= 50,000
 * M <= 100,000
 * K <= N
 */
public class Dining2 {
    public static void main(String[] args) throws Exception {

        int N, M, K;
        Scanner fin = new Scanner(new File("dining_gold_dec18/2.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // Read in the edges
        HashMap<Integer, Integer>[] edges = new HashMap[N+1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new HashMap<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(fin.nextLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].put(b, w);
            edges[b].put(a, w);
        }

        // Read in the location of hays and their values
        HashMap<Integer, Integer> hays = new HashMap<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(fin.nextLine());
            int h = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            hays.put(h, v);
        }

        // Get the optimal distance from barn to each pasture
        int[] distance_from_barn = new int[N+1];
        for (int i = 0; i <= N; i++) {
            distance_from_barn[i] = -1;
        }
        dijkstra(N, 0, distance_from_barn, edges);

        // Compute the starting distance from barn to each pasture through hay
        // It has two segments, the first is from barn to hay minus the hay yumminess, the
        // second segment is from hay to each pasture
        int[] distance_through_hays = new int[N+1];
        for (int i = 0; i <= N; i++) {
            distance_through_hays[i] = -1;
        }
        for (Integer h : hays.keySet()) {
            distance_from_barn[h] -= hays.get(h);
        }
        for (Integer h : hays.keySet()) {
            int starting_distance_at_this_hay = distance_from_barn[h];
            dijkstra(h, starting_distance_at_this_hay, distance_through_hays, edges);
        }

        PrintWriter fout = new PrintWriter(new File("dining.out"));
        // PrintWriter fout = new PrintWriter(System.out);
        for (int i = 1; i < N; i++) {
            int v = distance_through_hays[i] <= distance_from_barn[i] ? 1 : 0;
            System.out.println(v);
            fout.println(v);
        }
        fout.close();
    }

    static void dijkstra(
            int source,
            int starting_distance_at_source,
            int[] dist,
            HashMap[] edges) {
        dist[source] = starting_distance_at_source;
        LinkedList<Integer> visited = new LinkedList<>();
        visited.add(source);
        while (!visited.isEmpty()) {
            int v = visited.removeFirst();
            HashMap<Integer, Integer> neighbors = edges[v];
            for (int n : neighbors.keySet()) {
                if (dist[n] < 0 || dist[n] > dist[v] + (Integer) edges[v].get(n)) {
                    dist[n] = dist[v] + (Integer) edges[v].get(n);
                    visited.add(n);
                }
            }
        }
    }
}