import java.io.*;
import java.util.*;

public class MkMoney {
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("mkmoney.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] cost = new int[N];
        int[] revenue = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(fin.nextLine());
            cost[i] = Integer.parseInt(st.nextToken());
            revenue[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(cost));
        System.out.println(Arrays.toString(revenue));
        // p is an array where the i-th array given what's the max profit if spending i dollar
        // array length is i+1 to give 0 dollar 0 profit
        int[] p = new int[M+1];
        for (int i = 0; i <= M; i++) {
            p[i] = i;
        }
        for (int i = 1; i <= M; i++) {
            // check if the j-th cow can be purchased with i dolloar
            for (int j = 0; j < N; j++) {
                if (i >= cost[j]) {
                    // the decision
                    p[i] = Math.max(p[i], revenue[j] - cost[j] + p[i-cost[j]]);
                }
            }
            System.out.println("i=" + i + ", p=" + p[i]);
        }
        System.out.println(p[M]);
    }
}
