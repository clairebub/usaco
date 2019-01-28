import java.io.*;
import java.util.*;

public class Teamwork {
    static int N;
    static int K;
    static int[] values;
    static int[] max_values_sum;

    public static void main(String[] args) throws Exception {
        long start_time = System.currentTimeMillis();
//        Scanner fin = new Scanner(new File("teamwork.in"));
        Scanner fin = new Scanner(new File("data/teamwork_gold_dec18/7.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        values = new int[N];
        for (int i = 0; i < N; i++) {
            line = fin.nextLine();
            st = new StringTokenizer(line);
            values[i] = Integer.parseInt(st.nextToken());
        }
        //System.out.println("values: " + Arrays.toString(values));
        // max_values[i] contains the answer for the first ith cows.
        // the fist K cows are easy, you know how to calculate
        max_values_sum = new int[N];
        max_values_sum[0] = values[0];
        int max_v = values[0];
        for (int i = 1; i < K; i++) {
            max_v = Math.max(max_v, values[i]);
            max_values_sum[i] = (i+1) * max_v;
        }
        // now add one at a time
        for (int i = K; i < N; i++) {
            // i is the last element
            // j is the size of the last group
            for (int j = 1; j <= K; j++) {
                if (j == 1) {
                    max_v = values[i];
                    max_values_sum[i] = max_values_sum[i-1] + max_v;
                } else {
                    // max_v is the maximum value of single element for the (j) elements ending at ith
                    max_v = Math.max(max_v, values[i - j + 1]);
                    // v1 is the value when the last group has j elements
                    int v1 = max_values_sum[i - j] + max_v * j;
                    max_values_sum[i] = Math.max(max_values_sum[i], v1);
                }
            }
        }
        //System.out.println("values: " + Arrays.toString(max_values_sum));
        System.out.println("values: " + max_values_sum[N-1]);
        long end_time = System.currentTimeMillis();
        System.out.println("time: " + (end_time - start_time) + " ms");
    }
}
