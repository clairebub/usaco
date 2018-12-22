import java.io.*;
import java.util.*;

public class Dining {
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("dinning.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        int n_pastures = Integer.parseInt(st.nextToken());
        int m_trails = Integer.parseInt(st.nextToken());
        int k_hays = Integer.parseInt(st.nextToken());
        HashMap<Integer, HashMap<Integer, Integer>> trails = new HashMap<>();
        HashMap<Integer, Integer> hays = new HashMap<>();
        for (int i = 0; i < m_trails; i++) {
            line = fin.nextLine();
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (!trails.containsKey(a)) {
                trails.put(a, new HashMap<>());
            }
            if (!trails.containsKey(b)) {
                trails.put(b, new HashMap<>());
            }
            trails.get(a).put(b, cost);
            trails.get(b).put(a, cost);
        }
        for (int i = 0; i < k_hays; i++) {
            line = fin.nextLine();
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            hays.put(a, b);
        }

    }
}
