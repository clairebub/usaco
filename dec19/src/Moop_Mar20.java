import java.io.*;
import java.util.*;

public class Moop_Mar20 {

    static class MyTreeNode {
        public MyTreeNode(int val) {
            this.value = val;
            children = new ArrayList<>();
        }
        int value;
        ArrayList<MyTreeNode> children;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("moop.in"));
        PrintWriter pw = new PrintWriter(new File("moop.out"));

        int N = scanner.nextInt();
        long[] x = new long[N];
        long[] y = new long[N];
        for (int i = 0; i < N; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }

        LinkedList<Integer>[] edges = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new LinkedList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long delta_x = x[i] - x[j];
                long delta_y = y[i] - y[j];
                if (delta_x * delta_y >= 0) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }

        boolean[] visited = new boolean[N];
        Arrays.fill(visited, false);
        int n = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                n++;
                visited[i] = true;
                LinkedList<Integer> to_visit = edges[i];
                while (!to_visit.isEmpty()) {
                    int b = to_visit.removeFirst();
                    if (visited[b]) {
                        continue;
                    }
                    visited[b] = true;
                    to_visit.addAll(edges[b]);
                }
            }
        }

        pw.println(n);
        pw.close();
    }

}