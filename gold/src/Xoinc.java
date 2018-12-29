import java.io.*;
import java.util.*;

// N in [5, 2,000]
// coin_i in [1, 100,000]
public class Xoinc {
    static int best[][];
    static int N;
    static int[] money_left;
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("xoinc.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        N = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(fin.nextLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(coins));
        // entry best[i][j] gives you the best money you can make when
        // there's i coins left and you take j coins
        best = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == 0) {
                    best[i][j] = 0;
                } else {
                    best[i][j] = -1;
                }
            }
            System.out.println("best " + i + ", " + Arrays.toString(best[i]));
        }
        money_left = new int[N+1];
        money_left[1] = coins[N-1];
        for (int i = 2; i <= N; i++) {
            money_left[i] = money_left[i-1] + coins[N-i];
        }
        money_left[0] = 0;
        System.out.println("coins: " + Arrays.toString(coins));
        System.out.println("money left: " + Arrays.toString(money_left));

        best_money(1, 1);
        // best[c][p] = max{ i = 1 to min(c, 2p) of total_left - best[c-i][i] }.
        best_money(N, 1);
        System.out.println(best[N][1]);

    }

    // when the number of coins left is coins_left, the best money you can make when take coins_to_take
    // number of coins
    static int best_money(int coins_left, int coins_taken_prev_move) {
        if (coins_left == 0) {
            return 0;
        }
        if (best[coins_left][coins_taken_prev_move] > 0) {
            return best[coins_left][coins_taken_prev_move];
        }
        int best_money_left_for_me = 0;
        for (int i = 1; i <= Math.min(coins_taken_prev_move * 2, coins_left); i++) {
            int opponent_money = best_money(coins_left-i, i);
            int money_left_for_me = money_left[coins_left] - opponent_money;
            if (best_money_left_for_me < money_left_for_me) {
                best_money_left_for_me = money_left_for_me;
            }
        }
        best[coins_left][coins_taken_prev_move] = best_money_left_for_me;
        System.out.println(String.format("%d %d %d", coins_left, coins_taken_prev_move, best_money_left_for_me));
        return best_money_left_for_me;
    }
}
