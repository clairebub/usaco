import java.io.*;
import java.util.*;

public class Talent {
    // N <= 250, total combination is 2^250
    // W <= 1,000
    // Wi <= 1,000,000
    static class TalentWeight {
        int w, t;

        public TalentWeight(int w, int t) {
            this.w = w;
            this.t = t;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d, %.3f)", w, t, 1.0*t/w);
        }
    }
    public static void main(String[] args) throws Exception {
        long start_time = System.currentTimeMillis();
//        Scanner fin = new Scanner(new File("talent_gold_open18/3.in"));
        Scanner fin = new Scanner(new File("talent.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        ArrayList<TalentWeight> tw = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(fin.nextLine());
            int w = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            tw.add(new TalentWeight(w, t));
        }
        System.out.println(tw);
        Collections.sort(tw, new Comparator<TalentWeight>() {
            @Override
            public int compare(TalentWeight o1, TalentWeight o2) {
                return 1.0*o1.t/o1.w - 1.0*o2.t/o2.w > 0 ? -1 : 1;
            }
        });
        System.out.println(tw);
        int total_w = 0;
        int total_t = 0;
        for (int i = 0; i < N; i++) {
            total_w += tw.get(i).w;
            total_t += tw.get(i).t;
            if (total_w >= W) {
                System.out.println(String.format("i=%d total_w=%d total_t=%d", i, total_w, total_t));
                break;
            }
        }
        int r = (int) Math.floor(1.0*total_t/total_w*1000);
        System.out.println("time: " + (System.currentTimeMillis() - start_time) + " ms");
        System.out.println("w=" + W + ", r=" + r);
        PrintWriter fout = new PrintWriter(new File("talent.out"));
        fout.println(r);
        fout.close();
    }
}
