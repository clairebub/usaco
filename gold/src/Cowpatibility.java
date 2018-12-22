import java.io.*;
import java.util.*;

public class Cowpatibility {
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("cowpatibility.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        int n_cows = Integer.parseInt(st.nextToken());
        int[][] flavors = new int[n_cows][5];
        for (int i = 0; i < n_cows; i++) {
            line = fin.nextLine();
            st = new StringTokenizer(line);
            for(int j = 0; j < 5; j++) {
                int f = Integer.parseInt(st.nextToken());
                flavors[i][j] = f;
            }
            Arrays.sort(flavors[i]);
//            System.err.println(Arrays.toString(flavors[i]));
        }
        int noOverlap = 0;
        for (int i = 0; i < n_cows; i++) {
            HashSet<Integer> x = new HashSet<>();
            for (int k = 0; k < 5; k++) {
                x.add(flavors[i][k]);
            }
            for (int j = i+1; j < n_cows; j++) {
                boolean foundIt = false;
                for (int k = 0; k < 5; k++) {
                    if (x.contains(flavors[j][k])) {
                        foundIt = true;
                        break;
                    }
                }
                if (!foundIt) {
                    noOverlap++;
                }
            }
        }
        PrintWriter fout = new PrintWriter(new File("cowpatibility.out"));
        fout.println(noOverlap);
        fout.close();
    }
}
