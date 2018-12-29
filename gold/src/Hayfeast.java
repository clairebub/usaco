import java.io.*;
import java.util.*;

public class Hayfeast {

    static int answer = -1;
    static int N;
    static int M;
    static int[][] fs;
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("hayfeast.in"));
        N = fin.nextInt();
        M = fin.nextInt();
        fs = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            fs[i][0] = fin.nextInt();
            fs[i][1] = fin.nextInt();
            // System.out.println(Arrays.toString(fs[i]));
        }

        for (int i = 1; i <= N; i++) {
            grow(i, i, fs[i][0], fs[i][1]);
        }
        System.out.println(answer);
    }

    static void grow(int i_start, int i_end, int meal_flavor, int meal_spice) {
        if (meal_flavor >= M) {
            if (answer == -1 || meal_spice < M) {
                answer = meal_spice;
            }
            return;
        }
        if (i_start > 1) {
            grow(i_start - 1, i_end, meal_flavor + fs[i_start-1][0], Math.max(meal_spice, fs[i_start-1][1]));
        }
        if (i_end < N) {
            grow(i_start, i_end+1, meal_flavor + fs[i_end+1][0], Math.max(meal_spice, fs[i_end+1][1]));
        }
    }
}
