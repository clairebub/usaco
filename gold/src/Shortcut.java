import java.io.*;
import java.util.*;

public class Shortcut {
    public static void main(String[] args) throws Exception {
        int N, M, T;
        int cows[];
        Scanner fin = new Scanner(new File("shortcut.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cows = new int[N+1];
        st = new StringTokenizer(fin.nextLine());
        for (int i = 1; i <= N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
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
        int dist[] = new int[N+1];
        ArrayList path[] = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            path[i] = new ArrayList();
        }
        dijkstra(1, 0, dist, edges);

        System.out.println(N);

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
