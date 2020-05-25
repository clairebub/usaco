import java.io.*;
import java.util.*;

public class Dec19_MilkPump {
    static class Node {
        int node;
        int cost;
        int flowrate;

        Node(int a, int b, int c) {
            node = a;
            cost = b;
            flowrate = c;
        }
    }

    static class Comp implements Comparator<Node> {
        public int compare(Node a, Node b) {
            double r1 = a.flowrate / a.cost;
            double r2 = b.flowrate / b.cost;
            return (int) (r2 - r1);
        }
    }

    public static int[] cost;
    public static int[] flowrate;
    public static double[] ratios;
    public static ArrayList<Integer> processed;
    public static @SuppressWarnings("unchecked")
    LinkedList<Node>[] adj;
    public static PriorityQueue<Node> pq;

    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("pump.in"));
        PrintWriter pw = new PrintWriter(new File("pump.out"));
        // debug outputs
        // pw = new PrintWriter(System.out);
        int N = fin.nextInt();
        int M = fin.nextInt();
        adj = new LinkedList[N];
        cost = new int[N];
        flowrate = new int[N];
        ratios = new double[N];
        processed = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj[i] = new LinkedList<>();
            flowrate[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            int node1 = fin.nextInt() - 1;
            int node2 = fin.nextInt() - 1;
            int c = fin.nextInt();
            int f = fin.nextInt();
            adj[node1].add(new Node(node2, c, f));
            adj[node2].add(new Node(node1, c, f));
            if (node1 == 0) {
                cost[node2] = c;
                flowrate[node2] = f;
            }
            if (node2 == 0) {
                cost[node1] = c;
                flowrate[node1] = f;
            }
        }
        pq = new PriorityQueue<>(1, new Comp());
        for (int i = 0; i < N; i++) {
            ratios[i] = Integer.MIN_VALUE;
        }

        pq.add(new Node(0, 0, 0));

        ratios[0] = 0;
        while (processed.size() != N) {
            int u = pq.remove().node;
            processed.add(u);

            neighbors(u);
        }
        pw.println((int) (ratios[N - 1] * Math.pow(10, 6)));
        pw.close();

    }

    static void neighbors(int u) {
        int edgeCost;
        int edgeFlow;
        int newCost;
        int newFlow = -1;
        double edgeRatio = -1;
        double newRatio = -1;

        for (int i = 0; i < adj[u].size(); i++) {
            Node v = adj[u].get(i);

            if (!processed.contains(v.node)) {
                edgeCost = v.cost;
                edgeFlow = v.flowrate;
                //System.out.println(edgeCost + " " + edgeFlow);
                newCost = cost[u] + edgeCost;
                //System.out.println(cost[u]);
                newFlow = Math.min(flowrate[u], edgeFlow);
                //System.out.println(newCost + " " + newFlow);
                newRatio = (1.0) * newFlow / newCost;

                if (newRatio > ratios[v.node]) {
                    cost[v.node] = newCost;
                    flowrate[v.node] = newFlow;
                    ratios[v.node] = newRatio;
                }
                pq.add(new Node(v.node, newCost, newFlow));
            }
        }
    }
}