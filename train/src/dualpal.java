/*
 ID: claire.2
 LANG: JAVA
 TASK: dualpal
 */
import java.util.*;
import java.io.*;

public class dualpal {

    void run(Scanner scanner, PrintWriter pw) throws Exception {
        int N = scanner.nextInt();
        int S = scanner.nextInt();

        int found = 0;
        int num = S+1;
        while (found < N) {
            if (isDualPal(num)) {
                pw.println(num);
                ++found;
            }
            ++num;
        }
        pw.close();
    }

    boolean isDualPal(int x) {
        int palin_count = 0;
        for (int b = 2; b <= 10; b++) {
            if (isPalin(x, b)) {
                palin_count++;
            }
            if (palin_count >= 2) {
                return true;
            }
        }
        return false;
    }

    boolean isPalin(int x, int base) {
//        System.out.println("base=" + base + ", x=" + x);
        ArrayList<Integer> digits = new ArrayList<>();
        while (x >= base) {
            int d = x % base;
            digits.add(d);
            x = (x-d) / base;
        }
        digits.add(x);
        for (int i = 0; i < digits.size()/2; i++) {
            if (digits.get(i) != digits.get(digits.size()-i-1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("dualpal.in"));
        PrintWriter pw = new PrintWriter("dualpal.out");
        new dualpal().run(scanner, pw);
    }
}
