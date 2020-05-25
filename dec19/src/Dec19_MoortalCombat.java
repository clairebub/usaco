import java.io.*;
import java.util.*;

public class Dec19_MoortalCombat {

    static int N, M, K;
    static String S;
    static int[][] cost;
    static HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }

    static void run(int ii) throws Exception {
        String fin_name = String.format("data/milkvisits_gold_dec19/%d.in", ii);
        String fout_name = String.format("data/milkvisits_gold_dec19/s_%d.out", ii);
        if (ii == 0) {
            fin_name = "cowmbat.in";
            fout_name = "cowmbat.out";
        }
        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));
        // debug outputs
        pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = st.nextToken();

        cost = new int[M][M];
        // read in the graph
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cost[i][j]= Integer.parseInt(st.nextToken());
                pw.print(cost[i][j] + " ");
            }
            pw.println();
        }

        //for (int i = 0; )
        pw.flush();
        pw.close();
    }

}
