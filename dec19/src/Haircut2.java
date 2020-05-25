import java.io.*;
import java.util.*;

public class Haircut2 {

    public static void main(String[] args) throws Exception {


        Scanner fin = new Scanner(new File("haircut.in"));
        PrintWriter pw = new PrintWriter(new File("haircut.out"));

        int N;
        N = fin.nextInt();
        int[] strands = new int[N];
        for (int i = 0; i < N; i++) {
            strands[i] = fin.nextInt();
        }
        int[] answer = new int[N];
        answer[N-1] = num_of_inversions(strands, N-1);
        for (int i = N-2; i >= 0; i--) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (strands[j] == i) {
                    count++;
                }
            }
            answer[i] = answer[i+1] - count;
        }
        /*
        for (int i = 0; i < N; i++) {
            pw.println(num_of_inversions(strands, i));
        } */
        for (int i = 0; i < N; i++) {
            pw.println(answer[i]);
        }

        pw.close();

    }

    static int num_of_inversions(int[] strands, int d) {
        int[] x = Arrays.copyOfRange(strands, 0, strands.length);
        for (int i = 0; i < strands.length; i++) {
            if (x[i] > d) {
                x[i] = d;
            }
        }
        return mergeSortAndCount(x, 0, x.length - 1);
    }

    static int mergeAndCount(int[]x, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(x, l, m+1);
        int[] right = Arrays.copyOfRange(x, m+1, r+1);
        int i = 0, j = 0, k = l, count = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                x[k++] = left[i++];
            } else {
                x[k++] = right[j++];
                count += (m+1) - (l+i);
            }
        }
        while (i<left.length) {
            x[k++] = left[i++];
        }
        while (j < right.length) {
            x[k++] = right[j++];
        }
        return count;
    }

    static int mergeSortAndCount(int[] x, int l, int r) {
        int count = 0;

        if (l < r) {
            int m = (l + r)/2;
            count += mergeSortAndCount(x, l, m);
            count += mergeSortAndCount(x, m+1, r);
            count += mergeAndCount(x, l, m, r);
        }
        return count;
    }
}
