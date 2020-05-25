import java.io.*;
import java.util.*;

public class SocDist_Mar20 {

    static int N;
    static class Interval {
        int a;
        int b;

        public Interval(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "[" + a + ", " + b + "]";
        }
    }

    static class IntervalComparator implements Comparator<Interval> {


        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.a - o2.a;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("socdist.in"));
        PrintWriter pw = new PrintWriter(new File("socdist.out"));

        N = scanner.nextInt();
        int M = scanner.nextInt();
        ArrayList<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            intervals.add(new Interval(a, b));
        }
        Collections.sort(intervals, new IntervalComparator());
        int hi = (intervals.get(M-1).b - intervals.get(0).a) / (N-1);
        int lo = 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (mid == lo) {
                lo = hi;
                break;
            }
            if (can_do(intervals, mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        int d = (lo + hi) / 2;
        pw.println(d);
        pw.close();
    }

    public static boolean can_do(ArrayList<Interval> intervals, int d) {
        int n = 0;
        int last = -1;
        for (Interval i : intervals) {
            int a = i.a;
            int b = i.b;
            if (last < 0) {
                n++;
                last = a;
            }
            // can this interval even place 1
            if (last + d > b) {
                continue;
            }
            // can the first one be at a
            if (last + d <= a) {
                n++;
                last = a;
            } else {
                n++;
                last += d;
            }
            // now stepped into [a, b], how many can it accommodate between [last, a]
            int delta = (b - last) / d;
            last += delta * d;
            n += delta;
            if (n >= N) {
                break;
            }
        }
        return n >= N;
    }
}
