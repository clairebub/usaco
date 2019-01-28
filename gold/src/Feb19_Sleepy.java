import java.io.*;
import java.util.*;

public class Feb19_Sleepy {
  // 1-based index
  static class FenwickTree {
    int[] array;

    public FenwickTree(int size) {
      array = new int[size+1];
      Arrays.fill(array, 0);
    }

    // index is inclusive
    public int getSum(int index) {
      int sum = 0;
      while (index > 0) {
        sum += array[index];
        index -= index & (-index);
      }
      return sum;
    }

    public void update(int index, int v) {
      while (index <= array.length - 1) {
        array[index] += v;
        index += index & (-index);
      }
    }
  }

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
    int[] cows = new int[N];
    for(int i = 0; i < N; i++){
      cows[i] = Integer.parseInt(st.nextToken());
    }
    FenwickTree sorted = new FenwickTree(N);

    int last = N-1;
    sorted.update(cows[last], 1);
    while (last > 0) {
      if (cows[last-1] <= cows[last]) {
        sorted.update(cows[last-1], 1);
        last--;
      } else {
        break;
      }
    }

    String f_outname = String.format("/tmp/sleepy_gold_jan19/s_%d.out", ii);
    PrintWriter fout = new PrintWriter(new File(f_outname));
    fout.println(last);

    for (int i = 0; i < last; i++) {
      if (i != 0) {
        fout.print(" ");
      }
      sorted.update(cows[i], 1);
      int move_in_sorted = sorted.getSum(cows[i]) - 1;
      int move_in_unsorted = last - i - 1;
      fout.print(move_in_sorted + move_in_unsorted);
    }
    fout.println();
    fout.flush();
    fout.close();
  }
}
