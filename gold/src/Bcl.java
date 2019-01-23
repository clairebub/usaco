import java.io.*;
import java.util.*;

public class Bcl {

    static int N;
    static String[] cl;
    static String[] answer;

    public static void main(String[] args) throws Exception {
        long start_time = System.currentTimeMillis();
        Scanner fin = new Scanner(new File("bcl/bcl.1.in"));
        N = fin.nextInt();
        cl = new String[N];
        answer = new String[N];
        for(int i = 0; i < N; i++){
            cl[i] = fin.next();
        }
        int i_start = 0;
        int i_end = N-1;
        int i_answer = 0;

        System.out.println("cl: " + Arrays.toString(cl));
        pick(0, N-1, 0);
        for (int i = 0; i < N; i++) {
            System.out.print(answer[i]);
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() - start_time);
//        System.out.println(Arrays.toString(answer));
    }
    static void pick(int i_start, int i_end, int i_answer) {
        if (i_answer > N-1 || i_start > i_end) {
            return;
        }
        if (i_start == i_end) {
            answer[i_answer] = cl[i_start];
            return;
        }
        if (cl[i_answer].compareTo(cl[i_end]) < 0) {
            answer[i_answer] = cl[i_start];
            pick(i_start+1, i_end, i_answer+1);
        } else if (cl[i_answer].compareTo(cl[i_end]) > 0) {
            answer[i_answer] = cl[i_end];
            pick(i_start, i_end-1, i_answer+1);
        } else {
            int i = i_start;
            int j = i_end;
            while(cl[i].compareTo(cl[j]) == 0 && i < j) {
                i += 1;
                j -= 1;
            }
            if (i >= 0) { // equal all the way
                answer[i_answer] = cl[i_start];
                pick(i_start+1, i_end, i_answer+1);
            }
            if (cl[i].compareTo(cl[j]) < 0) {
                answer[i_answer] = cl[i_start];
                pick(i_start+1, i_end, i_answer+1);
            }
            if (cl[i].compareTo(cl[j]) > 0) {
                answer[i_answer] = cl[i_end];
                pick(i_start, i_end-1, i_answer+1);
            }
        }
    }
}
