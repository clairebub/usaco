import java.io.*;
import java.util.*;

public class Open18_Sort {
  static int N;
  static int cows[];

  // a fenwick tree to handle the prefix sum for array of 'size'
  // That array is 0-based
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

  public static void main(String[] args) throws Exception {
    FenwickTree ft = new FenwickTree(10);
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
    cows = new int[N+1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      cows[i] = Integer.parseInt(st.nextToken());
    }

    TreeMap<Integer, Integer> sortedCows = new TreeMap<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        if (cows[o1.intValue()] < cows[o2.intValue()]) {
          return -1;
        } else if (cows[o1.intValue()] > cows[o2.intValue()]) {
          return 1;
        } else {
          if (o1 < o2) {
            return -1;
          } else if (o1 > o2) {
            return 1;
          } else {
            return 0;
          }
        }
      }
    });

    for (int i = 1; i <= N; i++) {
      sortedCows.put(i, cows[i]);
    }

    FenwickTree ft = new FenwickTree(N);
    int answer = 1;
    int right_pos = 0;
    for (Map.Entry<Integer, Integer> entry : sortedCows.entrySet()) {
      right_pos++;
      int wrong_pos = entry.getKey();
      ft.increment(wrong_pos);
      int num_mis_placed = right_pos - ft.prefix_sum(right_pos);
      answer = Math.max(answer, num_mis_placed);
    }

    System.out.println(answer);
    pw.println(answer);
    pw.flush();
    pw.close();
  }

}
