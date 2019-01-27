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
    String line = fin.readLine();
    StringTokenizer st = new StringTokenizer(line);
    int N = Integer.parseInt(st.nextToken());

    ArrayList<Integer> unsorted = new ArrayList<>();
    line = fin.readLine();
    st = new StringTokenizer(line);
    for(int i = 0; i < N; i++){
      int x = Integer.parseInt(st.nextToken());
      unsorted.add(x);
    }

    int index = N - 1;
    int prev = unsorted.get(N-1);
    boolean keepgoing = true;
    while(index >= 0 && keepgoing){
      index--;
      if(unsorted.get(index) > prev){
        keepgoing = false;
      }
      prev = unsorted.get(index);
    }

    ArrayList<Integer> sorted = new ArrayList<>();
    sorted.addAll(unsorted.subList(index + 1, N));

    String f_outname = String.format("/tmp/sleepy_gold_jan19/s_%d.out", ii);
    PrintWriter fout = new PrintWriter(new File(f_outname));
    int actualsize = unsorted.size() - sorted.size();
    fout.println(actualsize);
    boolean is_first = true;
    int unsorted_start = 0;
    while(actualsize > 0){
      int next = unsorted.get(unsorted_start);
      unsorted_start++;
      actualsize--;
      int insertindex = - Collections.binarySearch(sorted, next) - 1;
      sorted.add(insertindex, next);
      if (!is_first) {
        fout.print(" ");
      }
      fout.print(actualsize + insertindex);
      is_first = false;
    }
    fout.println();
    fout.flush();
    fout.close();
  }
}
