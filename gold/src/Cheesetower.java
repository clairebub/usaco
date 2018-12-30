import java.io.*;
import java.util.*;

public class Cheesetower {

    static int N, T, K;
    static int[][] vh;
    static int[] values_compressed;
    static int[] values_not_compressed;
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("cheesetower.in"));
        N = fin.nextInt();
        T = fin.nextInt();
        K = fin.nextInt();
        vh = new int[N][2];
        for (int i = 0; i < N; i++) {
            vh[i][0] = fin.nextInt();
            vh[i][1] = fin.nextInt();
//            System.out.println(Arrays.toString(cheeses[i]));
        }
        values_compressed = new int[T+1];
        values_not_compressed = new int[T+1];
        for (int i = 0; i <= T; i++) {
            values_compressed[i] = -1;
            values_not_compressed[i] = -1;
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (T < vh[i][1]) {
                continue;
            }
            int v = vh[i][0] + tower_value(T-vh[i][1], vh[i][1] >= K ? true : false);
            if (answer < v) {
                answer = v;
            }
        }
        System.out.println(answer);
    }

    static int tower_value(int capacity_left, boolean compress_at_top) {
        if (compress_at_top) {
            if (values_compressed[capacity_left] > 0) {
                return values_compressed[capacity_left];
            }
        }
        if (!compress_at_top) {
            if (values_not_compressed[capacity_left] > 0) {
                return values_not_compressed[capacity_left];
            }
        }
        int v_max = 0;
        for (int i = 0; i < N; i++) {
            int h_i = compress_at_top ?  vh[i][1] / 5 * 4 : vh[i][1];
            if (capacity_left < h_i) {
                continue;
            }
            int v = vh[i][0] + tower_value(capacity_left-h_i, compress_at_top);
            if (v_max < v) {
                v_max = v;
            }
        }
        if (compress_at_top) {
            values_compressed[capacity_left] = v_max;
        }
        if (!compress_at_top) {
            values_not_compressed[capacity_left] = v_max;
        }
        return v_max;
    }
}
