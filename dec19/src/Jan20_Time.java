import java.io.*;
import java.util.*;

public class Jan20_Time {
    static class State {
        int node;
        int money;
        int time;

        State(int node, int money, int time) {
            this.node = node;
            this.money = money;
            this.time = time;
        }
    }

    static class Path {
        int money, time;

        Path(int money, int time) {
            this.money = money;
            this.time = time;
        }
    }

    static int N, M, C;
    static int[] moonies;
    static HashMap<Integer, HashSet<Integer>> roads = new HashMap<>();
    static ArrayList<Path> paths = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }

    static void run(int ii) throws Exception {
        String fin_name = "time.in";
        String fout_name = "time.out";

        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));
        // debug outputs
        // pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        moonies = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            moonies[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (!roads.containsKey(x)) {
                roads.put(x, new HashSet<>());
            }
            roads.get(x).add(y);
        }
        Queue<State> q = new LinkedList<>();
        for (Integer n : roads.get(1)) {
            q.add(new State(n, moonies[n], 1));
        }

        while(!q.isEmpty()) {
            State current = q.remove();
            if (current.node == 1) {
                paths.add(new Path(current.money, current.time));
            } else {
                if (roads.containsKey(current.node)) {
                    for (Integer n : roads.get(current.node)) {
                        q.add(new State(n, moonies[n] + current.money, current.time + 1));
                    }
                }
            }
        }

        int profit = 0;
        for (Path p : paths) {
            int m = 0;
            int d = 0;
            int i = 0;
            while (true) {
                m += p.money;
                d += p.time;
                int a = m - C * d * d;
                if ( a > profit) {
                    profit = a;
                }
                if ( a < 0) {
                    break;
                }
                if (++i > 3) {
                    break;
                }
            }
        }


        pw.println(profit);
        pw.flush();
        pw.close();
        System.exit(0);

        ArrayList<Path> terminal_paths = new ArrayList<>();
        Queue<Path> q2 = new LinkedList<>();
        for (Path p : paths) {
            q2.add(p);
        }
        while(!q2.isEmpty()) {
            Path p1 = q2.remove();
            boolean canIncrease = false;
            for (Path p2 : paths) {
                Path p = new Path(p1.money + p2.money, p1.time + p2.time);
                int a = p1.money - C * p1.time * p1.time;
                int b = p.money - C * p.time * p.time;
                if (b > a) {
                    canIncrease = true;
                    q2.add(p);
                }
            }
                if (!canIncrease) {
                terminal_paths.add(p1);
            }
        }

        for (Path p : terminal_paths) {
            int a = p.money - C * p.time * p.time;
            if (a > profit) {
                profit = a;
            }
        }
        pw.println(profit);
        pw.flush();
        pw.close();
    }
}
