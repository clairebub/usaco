import java.io.*;
import java.util.*;

public class Feb19_Dishes {
  static int N;
  static int[] DISHES;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader("cowland.in"));
    PrintWriter pw = new PrintWriter(new File("cowland.out"));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    DISHES = new int[N];
    for (int i = 0; i < N; i++) {
      new StringTokenizer(br.readLine());
      DISHES[i] = Integer.parseInt(st.nextToken());
    }
    int res = N -1;
    FenwickTree ft = new FenwickTree(N);

    pw.println(res);
    pw.close();
  }

  static class FenwickTree {
    int size;
    int[] array;

    public FenwickTree(int size) {
      this.size = size;
      array = new int[this.size + 1];  // the entry.0 is not used
      Arrays.fill(array, 0);
    }

    // index is inclusive
    public int prefix_sum(int index) {
      int sum = 0;
      while (index > 0) {
        sum += array[index];
        index -= index & (-index);
      }
      return sum;
    }

    public void increment(int index) {
      update(index, array[index] + 1);
    }

    public void update(int index, int v) {
      if (index <= 0) {
        throw new IllegalArgumentException("Illegal index: " + index);
      }
      while (index <= this.size) {
        array[index] += v;
        index += index & (-index);
      }
    }
  }
}
