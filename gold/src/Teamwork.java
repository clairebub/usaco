import java.io.*;
import java.util.*;

public class Teamwork {
    static int N;
    static int K;
    static int[] values;
    static int[] max_values;

    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("teamwork.in"));
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
        System.out.println("values: " + Arrays.toString(values));
        max_values = new int[N];
        for (int i = 0; i < N; i++) {
            max_values[i] = -1;
        }
        int v = max_value_dp(0);
        System.out.println("max values: " + Arrays.toString(max_values));
        PrintWriter fout = new PrintWriter(new File("teamwork.out"));
        fout.println(v);
        fout.close();
    }

    public static int max_value_dp(int start_index) {
        // max_value is the max of the follow three situations
        // 1. single member (start_index) + max_value_dp (start_index + 1)
        // 2. double member (start_index, start_index + 1) + max_value_dp(start_index + 2)
        // 3. triple member (start_index, start_index+2, start_index_2) + max_value_dp(stat_index_3)

        // so how does the recursion end?
        // the recursion ends if there are 1 left, 2 left and 3 left, basically up to K left
        // which is to say start_index >= N - K
        if (max_values[start_index] > 0) {
            return max_values[start_index];
        }
        if (start_index >= N - K) {
            // if it the values has been computed, don't do it again
            // otherwise, calc and set the value for the whole K
            int max_e = values[N-1];
            max_values[N-1] = max_e;
            for (int j = 1; j < K; j++) {
                int i = N - 1 - j;
                if (values[i] > max_e) {
                    max_e = values[i];
                }
                max_values[i] = max_e * (j + 1);
            }
            return max_values[start_index];
        }

        int max_element = values[start_index];
        int max_v = values[start_index] + max_value_dp(start_index+1);
        for (int i = 1; i < K; i++) {
            if (values[start_index+i] > max_element) {
                max_element = values[start_index+i];
            }
            int v1 = max_element * i;
            int v2 = max_value_dp(start_index + i);
            int v = v1 + v2;
            if (v > max_v) {
                max_v = v;
            }
        }
        max_values[start_index] = max_v;
        return max_values[start_index];
    }
}
