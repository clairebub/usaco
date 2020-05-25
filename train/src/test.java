/**
 *
 ID: claire.2
 LANG: JAVA
 TASK: test
 */

import java.io.*;
import java.util.*;

public class test {

    void run(Scanner scanner, PrintWriter pw) throws Exception {
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int s = a + b;
        pw.println(s);
        pw.close();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("test.in"));
        PrintWriter pw = new PrintWriter("test.out");
        new test().run(scanner, pw);
    }
}
