import java.util.*;
import java.io.*;

public class Jan20_ThreeSum {

    static int N, Q;
    static int numbers[];
    static int s3[][];
    static int s2[][];

    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }

    static void run(int ii) throws Exception {
        String fin_name = "threesum.in";
        String fout_name = "threesum.out";

        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));
        // debug outputs
        // pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        s3 = new int[N+1][N+1];
        s2 = new int[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                s3[i][j] = 0;
                s2[i][j] = 0;
            }
        }

        numbers = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N-3+1; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += numbers[i+j];
            }
            if (sum == 0) {
                s3[i][i+2] = 1;
            }
        }

        // queries
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pw.println(three_sum(a, b));
        }
        pw.flush();
        pw.close();
    }

    static int three_sum(int a, int b) {
        if (b - a == 2) {
            return s3[a][b];
        }
        return three_sum(a, b-1) + two_sum(a, b-1, -numbers[b]);

    }

    static int two_sum(int a, int b, int s) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = a; i <= b; i++) {
            map.put(numbers[i], i);
        }
        int n = 0;
        for (int i = a; i <= b; i++) {
            int x = numbers[i];
            int y = s - x;
            if (map.containsKey(y) && map.get(y) != i) {
                n++;
            }
        }
        return n / 2;
    }
}
