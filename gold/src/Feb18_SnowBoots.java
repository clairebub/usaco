import java.io.*;
import java.util.*;

public class Feb18_SnowBoots {

  static int N, B;
  static int[] boot_capacity;
  static int[] boot_stride;
  static int[] snow_depth;
  static int[] tile_pre;
  static int[] tile_nxt;
  static int[] answers;

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii)throws Exception {
    String fin_name = String.format("data/snowboots_gold_feb18/%d.in", ii);
    String fout_name = String.format("data/snowboots_gold_feb18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "snowboots.in";
      fout_name = "snowboots.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());

    snow_depth = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      snow_depth[i] = Integer.parseInt(st.nextToken());
    }

    boot_capacity = new int[B + 1];
    boot_stride = new int[B + 1];
    for (int i = 1; i <= B; i++) {
      st = new StringTokenizer(br.readLine());
      boot_capacity[i] = Integer.parseInt(st.nextToken());
      boot_stride[i] = Integer.parseInt(st.nextToken());
    }

    answers = new int[B+1];

    // sort boots based on snow resistance
    ArrayList<Integer> boots_index = new ArrayList<>();
    for (int i = 1; i <= B; i++) {
      boots_index.add(i);
    }
    Collections.sort(boots_index, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return boot_capacity[o2.intValue()] - boot_capacity[o1.intValue()];
      }
    });

    // sort tiles based on snow depth
    ArrayList<Integer> tile_sorted = new ArrayList<>();
    for (int i = 1; i <= N; i++) {
      tile_sorted.add(i);
    }
    Collections.sort(tile_sorted, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return snow_depth[o2.intValue()] - snow_depth[o1.intValue()];
      }
    });

    tile_pre = new int[N+1];
    tile_nxt = new int[N+1];
    for (int i = 1; i <= N; i++) {
      tile_pre[i] = i - 1;
      tile_nxt[i] = i + 1;
    }
    int j = 0;
    int maxStep = 1;
    for (int i = 0; i < B; i++) {
      int boot = boots_index.get(i);
      while (true) {
        int tile = tile_sorted.get(j);
        // remove the tile that no boots can stand on it
        if (snow_depth[tile] > boot_capacity[boot]) {
          int before = tile_pre[tile];
          int after = tile_nxt[tile];
          tile_nxt[before] = after;
          tile_pre[after] = before;
          maxStep = Math.max(maxStep, after - before);
        } else {
          break;
        }
        j++;
        if (j == N) {
          break;
        }
      }
      answers[boot] = boot_stride[boot] >= maxStep ? 1 : 0;
    }
    for (int i = 1; i <= B; i++) {
//      System.out.println(answers[i]);
      pw.println(answers[i]);
    }
    pw.flush();
    pw.close();
  }


}
