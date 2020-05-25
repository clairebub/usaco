import java.io.*;
import java.util.*;

public class Haircut {
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("haircut.in"));
        int N = fin.nextInt();
        int[] hairs = new int[N];
        for (int i = 0; i < N; i++) {
            hairs[i] = fin.nextInt();
            if (hairs[i] >= N) {
                hairs[i] = N - 1;
            }
        }
        int[] answers = new int[N];
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (hairs[i] > hairs[j]) {
                    total++;
                }
            }
        }
        answers[N-1] = total;
        for (int i = 0; i < N - 1; i++){
            int j = N - i - 2;
            int count = 0;
            for (int k : hairs){
                if(k > j){
                    count++;
                }
                if(k == j){
                    total -= count;
                }
            }
            answers[N-2-i] = total;
        }
        PrintWriter pw = new PrintWriter(new File("haircut.out"));
        for (int i : answers){
            pw.println(i);
        }
        pw.close();
    }

}
