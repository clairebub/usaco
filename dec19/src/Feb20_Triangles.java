import java.io.*;
import java.util.*;

public class Feb20_Triangles {

    static int N;
    static int[][] Fences;
    static HashMap<Integer, ArrayList<Integer>> xPosts = new HashMap<>();
    static HashMap<Integer, ArrayList<Integer>> yPosts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }

    static void run(int ii) throws Exception {
        String fin_name = "triangles.in";
        String fout_name = "triangles.out";

        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));
        // debug outputs
        // pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Fences = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Fences[i][0] = x;
            Fences[i][1] = y;
            if (!xPosts.containsKey(x)) {
                xPosts.put(x, new ArrayList<>());
            }
            xPosts.get(x).add(i);

            if (!yPosts.containsKey(y)) {
                yPosts.put(y, new ArrayList<>());
            }
            yPosts.get(y).add(i);
        }


        long area_2 = 0;
        for (int i = 0; i < N; i++) {
            int x = Fences[i][0];
            int y = Fences[i][1];
            for (int j = 0; j < xPosts.get(x).size(); j++) {
                int i2 = xPosts.get(x).get(j);
                for (int k = 0; k < yPosts.get(y).size(); k++) {
                    int i3 = yPosts.get(y).get(k);
                    int h = Fences[i2][1] - Fences[i][1];
                    int w = Fences[i3][0] - Fences[i][0];
                    area_2 += Math.abs(h * w);
                }
            }
        }
        long big = 1000000000L + 7;
        pw.println(area_2 % big);
        pw.close();
    }
}
