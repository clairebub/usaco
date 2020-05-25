import java.util.*;
import java.io.*;

public class Feb20_Swap {
    static int N;
    static int M;
    static int K;
    static int[] Cows;
    static int[][] Pairs;
    static HashMap<Integer, Integer> moving = new HashMap<>();


    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }

    static void run(int ii) throws Exception {
        String fin_name = "swap.in";
        String fout_name = "swap.out";

        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));
        // debug outputs
        // pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Cows = new int[N+1];
        for (int i = 1; i <= N; i++) {
            Cows[i] = i;
        }
        Pairs = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Pairs[i][0] = Integer.parseInt(st.nextToken());
            Pairs[i][1] = Integer.parseInt(st.nextToken());
        }
        // first round, see where it ends up
        for (int j = 0; j < M; j++) {
            int l = Pairs[j][0];
            int r = Pairs[j][1];
            int mid = (l + r) / 2;
            for (int k = l; k <= mid; k++) {
                int kk  = r - (k-l);
                int tmp = Cows[k];
                Cows[k] = Cows[kk];
                Cows[kk] = tmp;
            }
        }

        // after one round, moving N[i] to i
        int[] cowsMoving = new int[N+1];
        for (int i = 1; i <= N; i++) {
            cowsMoving[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            if (Cows[i] != i) {
                moving.put(Cows[i], i);
                cowsMoving[Cows[i]] = i;
            }
        }

        for (int x : moving.keySet()) {
            int y = x;
            int moves_made = 0;
            for (int k = 0; k < K; k++) {
                y = cowsMoving[y];
                moves_made++;
                if (y == x) {
                    break;
                }
            }
            if (moves_made < K) {
                for (int k = 0; k < K % moves_made; k++) {
                    y = cowsMoving[y];
                }
            }
            Cows[y] = x;
        }

        for (int i = 1; i <= N; i++) {
            pw.println(Cows[i]);
        }
        pw.close();
    }
}
