import java.io.*;
import java.util.*;

public class Open18_Sort {
  static int N;
  static int cows[];

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = String.format("data/sort_gold_open18/%d.in", ii);
    String fout_name = String.format("data/sort_gold_open18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "sort.in";
      fout_name = "sort.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    cows = new int[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      cows[i] = Integer.parseInt(st.nextToken());
    }
    int moo = do_sort(cows);
    pw.println(moo);
    pw.flush();
    pw.close();
//    System.out.println(moo);
  }

  static int do_sort(int[] A) {
    boolean sorted = false;
    int moo = 0;
    while (! sorted) {
      sorted = true;
      moo++;
      for (int i = 0; i <= N-2; i++) {
        if (A[i + 1] < A[i]) {
          int tmp = A[i + 1];
          A[i + 1] = A[i];
          A[i] = tmp;
        }
      }
      for (int i = N-2; i >= 0; i--) {
        if (A[i + 1] < A[i]) {
          int tmp = A[i + 1];
          A[i + 1] = A[i];
          A[i] = tmp;
        }
      }
      for (int i = 0; i <= N-2; i++) {
        if (A[i + 1] < A[i]) {
          sorted = false;
        }
      }
    }
    return moo;
  }
}
