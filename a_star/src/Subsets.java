import java.io.*;
import java.util.*;

public class Subsets {
    public static void run() throws Exception {
        Scanner fin = new Scanner(new File("setsub2.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N];
        line = fin.nextLine();
        st = new StringTokenizer(line);
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        //
        boolean[] choices = new boolean[N];
        for (int i = 0; i < N; i++) {
            choices[i] = false;
        }

        visit_all(numbers, choices,0);
    }

    static void visit_all(int[] values, boolean[] choices, int currentIndex) {
        if (currentIndex == values.length) {
            boolean printed = false;
            for(int i = choices.length - 1; i >= 0; i--) {
                if (choices[i]) {
                    // if the last index is chosen, it means print the first number
                    System.out.print(values[choices.length - 1 - i]);
                    printed = true;
                }
            }
            if (printed) {
                System.out.println();
            }
            return;
        }
        choices[currentIndex] = false;
        visit_all(values, choices, currentIndex+1);
        choices[currentIndex] = true;
        visit_all(values, choices, currentIndex+1);
    }
}
