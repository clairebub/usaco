import java.io.*;
import java.util.*;

public class Scales {
    public static void run() throws Exception {
        Scanner fin = new Scanner(new File("scales.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        int n_weights = Integer.parseInt(st.nextToken());
        int max_mass = Integer.parseInt(st.nextToken());
        int[] weights = new int[n_weights];
        int n_good_weights = 0;
        for (int i = 0; i < n_weights; i++) {
            weights[i] = Integer.parseInt(fin.nextLine());
        }
        // so from n_good_weights
        int max_weight = knapSack(max_mass, weights, n_weights - 1);
        System.out.println(max_weight);
    }

    // going backwards from the current index using the array of weights, fill as
    // much as you can for up to remaining_weight
    static int knapSack(int remaining_weight, int[] weights, int currentIndex) {
        if (currentIndex < 0 || remaining_weight <= 0) {
            return 0;
        }
        if (weights[currentIndex] > remaining_weight) {
            return knapSack(remaining_weight, weights, currentIndex-1);
        }
        // option 1 is choose the weight at current index, and maximize how you fill the reduced capacitiy with smaller weights.
        int weight_1 = weights[currentIndex] + knapSack(remaining_weight - weights[currentIndex], weights, currentIndex - 1);
        // option 2 is skip the weight at the current index, and maximum how you fill the reduced capacity with smaller weights.
        int weight_2 = knapSack(remaining_weight, weights, currentIndex - 1);
        // return whichever is better
        if (weight_1 >= weight_2) {
            return weight_1;
        } else {
            return weight_2;
        }
    }
}
