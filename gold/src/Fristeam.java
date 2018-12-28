import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Fristeam {

    static int N, F;
    static int[] R;
    static int[][] remainders;
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("fristeam.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        R = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(fin.nextLine());
            R[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(R));
        remainders = new int[N][F];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < F; j++) {
                remainders[i][j] = -1;
            }
        }
        int result = num_of_subsets_where_remainder_of_sum_is_r(N-1, 0);
        System.out.println(result);
    }

    // with the new number at index n, get the number of subsets where S % F == r
    static int num_of_subsets_where_remainder_of_sum_is_r(int n, int r) {
        if (n < 0) {
            return 0;
        }
        // memoization: if it's been calculated, don't calculate it again
        if (remainders[n][r] >= 0) {
            return remainders[n][r];
        }
        int x = R[n] % F;
        int y = (r - x + F) % F;

        // contribution from previous ones when you don't include the current one
        int s1 = num_of_subsets_where_remainder_of_sum_is_r(n - 1, r);
        // contribution from previous ones when you add the current one into each of the previous sets
        int s2 = num_of_subsets_where_remainder_of_sum_is_r(n - 1, y);
        // one more case is when yourself is remainder, you alone is a solution
        int s3 = x == r ? 1 : 0;
        int total = s1 + s2 + s3;
        remainders[n][r] = total; // memoization: remember what you calculated
        return remainders[n][r];
    }
}
