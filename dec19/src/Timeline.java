import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.*;

public class Timeline {
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("timeline.in"));
        PrintWriter pw = new PrintWriter(new File("timeline.out"));
        // debug outputs
         //pw = new PrintWriter(System.out);

        int n = fin.nextInt();
        int m = fin.nextInt();
        int c = fin.nextInt();
        int[] sessions = new int[n+1];
        int[] answer = new int[n+1];
        for (int i = 1; i <= n; i++) {
            answer[i] = sessions[i] = fin.nextInt();
        }

        int[] pred_counts = new int[n+1];
        ArrayList<Integer>[] succ = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            pred_counts[i] = 0;
            succ[i] = new ArrayList<>();
        }

        ArrayList<Integer>[] adj_nodes = new ArrayList[n+1];
        ArrayList<Integer>[] adj_weights = new ArrayList[n+1];

        for (int i = 0; i < c; i++) {
            int a = fin.nextInt();
            int b = fin.nextInt();
            int w = fin.nextInt();
            if (adj_nodes[a] == null) {
                adj_nodes[a] = new ArrayList<>();
                adj_weights[a] = new ArrayList<>();
            }
            adj_nodes[a].add(b);
            adj_weights[a].add(w);
            pred_counts[b]++;
            succ[a].add(b);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int a = 1; a <= n; a++) {
            if (pred_counts[a] == 0 && adj_nodes[a] != null) {
                q.add(a);
            }
        }
        while (!q.isEmpty()) {
            int a = q.remove();
            if (pred_counts[a] == 0 && adj_nodes[a] != null) {
                for (int i = 0; i < adj_nodes[a].size(); i++) {
                    int b = adj_nodes[a].get(i);
                    int w = adj_weights[a].get(i);
                    if (answer[a] + w > answer[b]) {
                        answer[b] = answer[a] + w;
                    }
                    pred_counts[b]--;
                    if (pred_counts[b] == 0 && adj_nodes[b] != null) {
                        q.add(b);
                    }
                }
            }
        }


        for (int i = 1; i <= n; i++) {
            pw.println(answer[i]);
        }
        pw.close();
    }
}
