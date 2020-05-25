/*
 ID: claire.2
 LANG: JAVA
 TASK: transform
 */

import java.io.*;
import java.util.*;

public class transform {

    void run(Scanner scanner, PrintWriter pw) throws Exception {
        int N = scanner.nextInt();
        char[][] from = new char[N][N];
        char[][] to = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = scanner.next();
            for (int j = 0; j < N; j++) {
                from[i][j] = s.toCharArray()[j];
            }
        }
        for (int i = 0; i < N; i++) {
            String s = scanner.next();
            for (int j = 0; j < N; j++) {
                to[i][j] = s.toCharArray()[j];
            }
        }

        if(is_same(to, do_transform_1(from))) {
            pw.println(1);
        } else if (is_same(to, do_transform_2(from))) {
            pw.println(2);
        } else if (is_same(to, do_transform_3(from))) {
            pw.println(3);
        } else if (is_same(to, do_transform_4(from))) {
            pw.println(4);
        } else if (is_same(to, do_transform_1(do_transform_4(from)))
                || is_same(to, do_transform_2(do_transform_4(from)))
                || is_same(to, do_transform_3(do_transform_4(from)))) {
            pw.println(5);
        } else if(is_same(to, from)) {
            pw.println(6);
        } else {
            pw.println(7);
        }
        pw.close();
    }

    boolean is_same(char[][] x, char[][] y) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (x[i][j] != y[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    char[][] do_transform_1(char[][] from) {
        char[][] to = new char[from.length][from.length];
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from.length; j++) {
                int row = j;
                int col = from.length - 1 - i;
                to[row][col] = from[i][j];
            }
        }
        return to;
    }

    char[][] do_transform_2(char[][] from) {
       return do_transform_1(do_transform_1(from));
    }

    char[][] do_transform_3(char[][] from) {
        return do_transform_2(do_transform_1(from));
    }

    char[][] do_transform_4(char[][] from) {
        char[][] to = new char[from.length][from.length];
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from.length; j++) {
                int row = i;
                int col = from.length - 1 - j;
                to[row][col] = from[i][j];
            }
        }
        return to;
    }

    void printSquare(char[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("transform.in"));
        PrintWriter pw = new PrintWriter("transform.out");
        new transform().run(scanner, pw);
    }
}
