import java.io.*;
import java.util.*;

public class Dec19_MilkVisits {

    static int N, M;
    static int[] milk_types;
    static HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }

    static void run(int ii) throws Exception {
        String fin_name = String.format("data/milkvisits_gold_dec19/%d.in", ii);
        String fout_name = String.format("data/milkvisits_gold_dec19/s_%d.out", ii);
        if (ii == 0) {
            fin_name = "milkvisits.in";
            fout_name = "milkvisits.out";
        }
        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));
        // debug outputs
        pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        // read in the milk types at each farm
        milk_types = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            milk_types[i] = Integer.parseInt(st.nextToken());
            // pw.println(milk_types[i]);
        }

        // read in the graph
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!edges.containsKey(a)) {
                edges.put(a, new HashSet<Integer>());
            }
            edges.get(a).add(b);
        }


        // read in the questions
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int mt = Integer.parseInt(st.nextToken());
            if (is_it_in(start, end, mt)) {
                pw.print(1);
            } else {
                pw.print(0);
            }
        }
        pw.println();
        pw.flush();
        pw.close();
    }

    static boolean is_it_in(int start, int end, int mt) {
        System.out.println("deebug: " + start + ", " + end + ", " + mt);
        // all the visited cow types from the start to this node
        HashMap<Integer, HashSet<Integer>> visited_types = new HashMap<>();

        Deque<Integer> deque = new LinkedList<Integer>();
        deque.add(start);
        while (!deque.isEmpty()) {
            int a = deque.poll();
            if (!visited_types.containsKey(a)) {
                visited_types.put(a, new HashSet<>());
                visited_types.get(a).add(milk_types[a]);
            }
            if (a == end) {
                break;
            }
            if (edges.containsKey(a)) {
                for (Integer b : edges.get(a)) {
                    deque.add(b);
                }
            }
            // System.out.println("deebug: " + a + ", " + edges);

        }

        return visited_types.get(end).contains(mt);
    }
}