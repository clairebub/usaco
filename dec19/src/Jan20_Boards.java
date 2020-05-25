import java.io.*;
import java.util.*;

public class Jan20_Boards {

    static int N;
    static int P;
    static HashMap<String, String> boards = new HashMap<>();
    static HashMap<String, String> reached_points = new HashMap<>();


    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }

    static void run(int ii) throws Exception {
        String fin_name = "boards.in";
        String fout_name = "boards.out";

        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));
        // debug outputs
        pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            String x1 = st.nextToken();
            String y1 = st.nextToken();
            String x2 = st.nextToken();
            String y2 = st.nextToken();
            String k = x1 + ":" + y1;
            String v = x2 + ":" + y2;
            boards.put(k, v);
        }

    }
}
