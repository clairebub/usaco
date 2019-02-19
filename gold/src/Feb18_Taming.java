import java.io.*;
import java.util.*;

public class Feb18_Taming {

  public static void main(String[] args) throws Exception {
    run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
  }

  static void run(int ii) throws Exception {
    String fin_name = String.format("data/taming_gold_feb18/%d.in", ii);
    String fout_name = String.format("data/taming_gold_feb18/s_%d.out", ii);
    if (ii == 0) {
      fin_name = "taming.in";
      fout_name = "taming.out";
    }
    BufferedReader br = new BufferedReader(new FileReader(fin_name));
    PrintWriter pw = new PrintWriter(new File(fout_name));
    StringTokenizer st = new StringTokenizer(br.readLine());
  }
}