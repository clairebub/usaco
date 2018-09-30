import java.util.HashSet;

public class CatchCow {
    //
    // Return the minimum steps it take to get from n to k, where you can either move 1 step, or go from x to 2x
    //
    static int run(int n, int k) {
        if (n == k) {
            return 0;
        }
        int steps = 0;
        HashSet<Integer> startingPos = new HashSet<Integer>();
        startingPos.add(n);
        boolean foundIt = false;
        while (!foundIt) {
            steps += 1;
            System.out.println(steps);
            System.out.println(startingPos);
            HashSet<Integer> newStartingPos = new HashSet<Integer>();
            for (int pos : startingPos) {
                if (pos + 1 == k || pos - 1 == k || 2 * pos == k) {
                    foundIt = true;
                    break;
                }
                if (pos + 1 >= 0) {
                    newStartingPos.add(pos + 1);
                }
                if (pos - 1 >= 0) {
                    newStartingPos.add(pos - 1);
                }
                if (pos < k && pos > 0) {
                    newStartingPos.add(2 * pos);
                }
            }
            startingPos.clear();
            startingPos.addAll(newStartingPos);
        }
        return steps;
    }
}
