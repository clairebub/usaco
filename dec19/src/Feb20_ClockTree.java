import java.util.*;
import java.io.*;

public class Feb20_ClockTree {
    static class State {
        int node;
        int[] time;

        State(int i, int[] t) {
            this.node = i;
            this.time = t;
        }
    }

    static int N;
    static int[] Times;
    static LinkedList<Integer>[] adj;


    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }

    static void run(int ii) throws Exception {
        String fin_name = "clocktree.in";
        String fout_name = "clocktree.out";

        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));
        // debug outputs
        // pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Times = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Times[i] = Integer.parseInt(st.nextToken());
        }

        adj = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            adj[node1].add(node2);
            adj[node2].add(node1);
        }

        int answer = 0;
        outer: for(int i = 0; i < N; i++){
            Queue<State> q = new LinkedList<>();
            State first = new State(i, Times);
            q.add(first);
            while (!q.isEmpty())
            {
                State current = q.remove();
                boolean done = true;
                for (int k : current.time){
                    if (k != 12){
                        done = false;
                    }
                }
                if(done){
                    answer++;
                    continue outer;
                }
                for (int l : adj[current.node]) {
                    int[] nextTimes = new int[N];
                    for (int p = 0; p < N; p++){
                        nextTimes[p] = current.time[p];
                    }
                    State nextState = new State(l, nextTimes);
                    if (nextTimes[l] != 12){
                        nextTimes[l]++;
                        q.add(nextState);
                    }
                }
            }
        }
        pw.println(answer);
        pw.close();
    }
}
