import java.io.*;
import java.util.*;

public class Coldwater {

    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("coldwater.in"));
        int N = fin.nextInt();
        int C = fin.nextInt();
        int[][] edges = new int[N+1][2];
        for (int i = 0; i < C; i++) {
            int a = fin.nextInt();
            int b = fin.nextInt();
            int c = fin.nextInt();
            edges[a][0] = b;
            edges[a][1] = c;
            System.out.println(Arrays.toString(edges[a]));
        }
        int[] dist = new int[N+1];
        dist[1] = 1;
        bfs(1, dist, edges);
        for (int i = 1; i <= N; i++) {
            System.out.println(dist[i]);
        }
    }

    static void bfs(
            int source,
            int[] dist,
            int[][] edges) {
        LinkedList<Integer> visited = new LinkedList<>();
        visited.add(source);
        while (!visited.isEmpty()) {
            int a = visited.removeFirst();
            for (int i = 0; i < edges[a].length; i++) {
                int b = edges[a][i];
                if (b > 0) { // if b == 0, it means just initialized but doesn't have a valid connection
                    dist[b] = dist[a] + 1;
                    visited.add(b);
                }
            }
        }
    }
}
