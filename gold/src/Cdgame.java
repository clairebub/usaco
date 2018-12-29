import java.io.*;
import java.util.*;

public class Cdgame {

    static class MinAndMax {
        public MinAndMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
        int min;
        int max;
    };

    static HashMap<Integer, Boolean> known_results = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("cdgame.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        int G = Integer.parseInt(st.nextToken());
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(fin.nextLine());
            int n = Integer.parseInt(st.nextToken());
            boolean a_winner = is_winner(n);
            System.out.println("n=" + n + ", " + (a_winner ? "Yes" : "No"));
        }
    }

    static boolean is_winner(int number) {
        if (number < 10) {
            return true;
        }
        if (known_results.containsKey(number)) {
            return known_results.get(number);
        }
        MinAndMax min_and_max = getMinAndMaxDigit(number);
        boolean a = !is_winner(number - min_and_max.min);
        boolean b = !is_winner(number - min_and_max.max);
        boolean result = a || b;
        known_results.put(number, result);
        return result;
    }

    static MinAndMax getMinAndMaxDigit(int number) {
        int min = -1;
        int max = -1;
        while (number >= 10) {
            int d = number % 10;
            if (d != 0) {
                if (min < 0 || min > d) {
                    min = d;
                }
                if (max < 0 || max < d) {
                    max = d;
                }
            }
            number = (number - d) / 10;
        }
        if (number != 0) {
            if (min < 0 || min > number) {
                min = number;
            }
            if (max < 0 || max < number) {
                max = number;
            }
        }
        return new MinAndMax(min, max);
    }
}
