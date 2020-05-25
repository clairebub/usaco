import java.io.*;
import java.util.*;

public class Cereal_Mar20 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("cereal.in"));
        PrintWriter pw = new PrintWriter(new File("cereal.out"));

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        boolean[] cereals = new boolean[M+1];
        Arrays.fill(cereals, true);

        int[] f = new int[N];
        int[] s = new int[N];
        for (int i = 0; i < N; i++) {
            f[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }

        for (int i = 0; i < N; i++) {
            int n = 0;
            Arrays.fill(cereals, true);
            for (int j = i; j < N; j++) {
                if (cereals[f[j]]) {
                    cereals[f[j]] = false;
                    n++;
                    continue;
                }
                if (cereals[s[j]]) {
                    cereals[s[j]] = false;
                    n++;
                    continue;
                }
            }
            pw.println(n);
        }
        pw.close();
    }
}
