import java.io.*;
import java.util.*;

public class Divgold {
    static int N;
    static int[] gold;
    static int sum;
    static int[] answer;
    static boolean[] good;

    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("divgold.in"));
        N = fin.nextInt();
        gold = new int[N];
        for(int i = 0; i < N; i++){
            gold[i] = fin.nextInt();
            sum += gold[i];
        }
        answer = new int[sum+1];
        good = new boolean[sum+1];
        for (int i = 0; i <= sum; i++) {
            answer[i] = 0;
            good[i] = false;
        }
        answer[0] = 1;
        good[0] = true; good[sum] = true;
        for (int i = 0; i < N; i++) {
            int v = gold[i];
            for (int j = sum / 2; j >= v; j--) {
                answer[j] = (answer[j] + answer[j - v]) % 1000000;
                if (good[j - v]) good[j] = true;
            }
        }

        int best = sum / 2;
        for (best = sum / 2; !good[best]; best--) {}

        System.out.println(Math.abs(best - (sum - best)));
        System.out.println(answer[best]);

    }
}
