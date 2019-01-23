import java.io.*;
import java.util.*;

public class OutOfSort {
  static int N;
  static int numbers[];

  public static void main(String[] args) throws Exception {
    System.out.println("hello");
    long start_time = System.currentTimeMillis();
    Scanner fin = new Scanner(new File("sort_gold_open18/9.in"));
    N = fin.nextInt();
    numbers = new int[N];
    for (int i = 0; i < N; i++) {
      numbers[i] = fin.nextInt();
    }
//    System.out.println(Arrays.toString(numbers));
    int moo = bubble_sort();
    System.out.println("moo=" + moo);
    System.out.println("elapsed time: " + (System.currentTimeMillis() - start_time) + " ms");
  }

  static int bubble_sort() {
    int moo = 0;
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      moo++;
      for (int i = 0; i < N-2; i++) {
        if (numbers[i+1] < numbers[i]) {
          int tmp = numbers[i];
          numbers[i] = numbers[i+1];
          numbers[i+1] = tmp;
        }
      }
      for (int i = N - 2; i >= 0; i--) {
        if (numbers[i+1] < numbers[i]) {
          int tmp = numbers[i];
          numbers[i] = numbers[i+1];
          numbers[i+1] = tmp;
        }
      }
      for (int i = 0; i <= N-2; i++) {
        if (numbers[i+1] < numbers[i]) {
          sorted = false;
        }
      }
    }
    return moo;
  }
}
