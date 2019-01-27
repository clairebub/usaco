import java.io.*;
import java.util.*;

public class Sleepy2 {
  public static void main(String[] args) throws Exception {
    for (int i = 1; i <= 15; i++) {
      long t1 = System.currentTimeMillis();
      run(i);
      long t2 = System.currentTimeMillis();
      System.out.println("i=" + i + ", ts=" + (t2-t1) + " ms");

    }
  }
  static void run(int ii) throws Exception {
    String fname = String.format("/tmp/sleepy_gold_jan19/%d.in", ii);
    BufferedReader fin = new BufferedReader(new FileReader(fname));

    StringTokenizer st = new StringTokenizer(fin.readLine());
    int N = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(fin.readLine());
    ArrayList<Integer> unsorted = new ArrayList<>();
    for(int i = 0; i < N; i++){
      int x = Integer.parseInt(st.nextToken());
      unsorted.add(x);
    }

    int start = N - 1;
    while (start > 0) {
      if (unsorted.get(start - 1) > unsorted.get(start)) {
        break;
      }
      start--;
    }

    ArrayList<Integer> sorted = new ArrayList<>();
    sorted.addAll(unsorted.subList(start, N));

    String f_outname = String.format("/tmp/sleepy_gold_jan19/s_%d.out", ii);
    PrintWriter fout = new PrintWriter(new File(f_outname));
    int num_to_move = unsorted.size() - sorted.size();
    fout.println(num_to_move);

    boolean is_first = true;
    int unsorted_start = 0;

    while (num_to_move > 0) {
      int next = unsorted.get(unsorted_start);
      unsorted_start++;
      num_to_move--;

      int insertindex = - Collections.binarySearch(sorted, next) - 1;
      sorted.add(insertindex, next);

      if (!is_first) {
        fout.print(" ");
      }
      fout.print(num_to_move + insertindex);
      is_first = false;
    }
    fout.println();
    fout.flush();
    fout.close();
  }
}
