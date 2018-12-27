import java.io.*;
import java.util.*;

public class MilkOrder {
    public static void main(String[] args) throws Exception {
        long start_time = System.currentTimeMillis();
        Scanner fin = new Scanner(new File("milkorder_gold_open18/1.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList> observations = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(fin.nextLine());
            int x = Integer.parseInt(st.nextToken());
            ArrayList<Integer> xx = new ArrayList<>();
            for (int j = 0; j < x; j++) {
                xx.add(Integer.parseInt(st.nextToken()));
            }
            observations.add(xx);
        }
        System.out.println(observations);
    }
}
