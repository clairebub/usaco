import java.io.*;
import java.util.*;

/**
 * N <= 100K
 *
 */
public class Sort {
    public static void main(String[] args) throws Exception {
        long start_time = System.currentTimeMillis();
        Scanner fin = new Scanner(new File("sort_gold_open18/6.in"));
        int N = fin.nextInt();
        int numbers[] = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = fin.nextInt();
        }
        do_sort(numbers);
        System.out.println("time: " + (System.currentTimeMillis() - start_time) + " ms");
        PrintWriter fout = new PrintWriter(System.out);
        fout.close();
    }

    static void do_sort(int[] A) {
        boolean sorted = false;
        int moo = 0;
        while (!sorted) {
            sorted = true;
            moo++;
            // move the largest
            for (int i = 0; i < A.length - 2; i++) {
                if (A[i+1] < A[i]) {
                    int x = A[i];
                    A[i] = A[i+1];
                    A[i+1] = x;
                }
            }
            for (int i = A.length - 2; i>=0; i--) {
                if (A[i+1] < A[i]) {
                    int x = A[i];
                    A[i] = A[i+1];
                    A[i+1] = x;
                }
            }
            for (int i = 0; i < A.length - 2; i++) {
                if (A[i+1] < A[i]) {
                    sorted = false;
                }
            }
        }
        System.out.println("moo: " + moo);
    }
}
