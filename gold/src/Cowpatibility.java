import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.*;
import java.math.MathContext;
import java.util.*;

public class Cowpatibility {
    /**
     * Pair-wise would be N*N, for N at 50K, it's 250M comparisons, that's a lot.
     * So, instead, for a given flavor, we see how many people have it as their favorite,
     * there's one million flavors, one person has 5 flavors, so at most F is at most 250K flavors will
     * be used. a flavor has on average 0.25 people use it.
     *
     * now for a given flavor, we have a list of people who uses it.
     * we end up of 250K flavors, each has a list of people, these are compatible people. but the same
     * pair of people might appear in different flavors, thus over estimate.
     * list of people in set of 2 flavors, -
     * list of people in set of 3 flavors, +
     * list of people in set of 4 flavors, -
     * list of people in set of 5 flavors, +
     *
     * how do we get list of people in set of 2 flavors, (F choose 2) is still too big. How do we do that?
     * there's another way, for a person who chose 5 favorites, it can have up to 31 combination of flavors
     * that it will be compatible. So for a person, it will find its compatible person in 31 set of flavors.
     *
     * so that's what we end up building:
     * flavor_set -> person list, one person will appear in 31 flavor sets, so we have 31N flavor sets.
     *
     * one step further, we generate more flavor sets, for a person has the following five flavors (A, B, C, D, E)
     * sorted, we generate the maps like the following different keys, 31 in total:
     * 1. A
     * 2. B
     * 3. C.
     * 4. D
     * ...
     * 5. A, B
     *
     * So another person with favorite flavors (A, B, O, P, Q), that guy will show up in the map keyed at (A), (B)
     * and (A, B), so how do we handle over counting? add the single and minus the double ones.
     */
    static int N;
    static int[][] FLAVORS;

    static class MatchKey {
        public  MatchKey() {
            n = 0;
            for (int i = 0; i < 5; i++) {
                flavors[i] = 0;
            }
        }
        int n;
        int[] flavors = new int[5];

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("n=");
            sb.append(n);
            sb.append(", flavors=[");
            for (int i = 0; i < n; i++) {
                sb.append(flavors[i]);
                sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof MatchKey)) {
                return false;
            }
            MatchKey mk = (MatchKey) obj;
            if (mk.n != n) {
                return false;
            }
            for (int i = 0; i < n; i++) {
                if (flavors[i] != mk.flavors[i]) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 37 * result + n;
            for (int i = 0; i < n; i++) {
                result = 37 * result + flavors[i];
            }
            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        long start_time = System.currentTimeMillis();
        Scanner fin = new Scanner(new File("cowpatibility_gold_dec18/10.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        FLAVORS = new int[N][5];
        HashMap<MatchKey, Integer> compat = new HashMap<>();

        for (int i = 0; i < N; i++) {
            line = fin.nextLine();
            st = new StringTokenizer(line);
            for(int j = 0; j < 5; j++) {
                int f = Integer.parseInt(st.nextToken());
                FLAVORS[i][j] = f;
            }
            Arrays.sort(FLAVORS[i]);
//            System.out.println(Arrays.toString(FLAVORS[i]));
            for (int j = 1; j <=31; j++) {
                MatchKey mk = genSubSet(FLAVORS[i], j);
                int count = compat.containsKey(mk) ? compat.get(mk) : 0;
                compat.put(mk, count+1);
            }
        }
        int[] inc_exc = {1, -1, 1, -1, 1};
        long num_overlap = 0;
        for (MatchKey mk : compat.keySet()) {
            int n_matched_flavors = mk.n;
            long n_matched_people = compat.get(mk);
            if (n_matched_flavors == 3 && n_matched_people > 1) {
//                System.out.println("mk: " + mk + ", " + n_matched_people);
            }
            n_matched_people = inc_exc[n_matched_flavors - 1] * n_matched_people * (n_matched_people - 1) / 2;
            num_overlap += n_matched_people;
        }

        // PrintWriter fout = new PrintWriter(new File("cowpatibility.out"));
        PrintWriter fout = new PrintWriter(System.out);
        fout.println("noOverlap: " + ((long)N*(N-1)/2 - num_overlap));
        long end_time = System.currentTimeMillis();
        fout.println(String.format("time: %d ms", end_time - start_time));
        fout.close();
    }

    // goes through the array to see which one should be included, given the x as a bitmap
    static MatchKey genSubSet(int[] flavors, int x) {
        MatchKey mk = new MatchKey();
        for (int i = 0; i < 5; i++) {
            if (((1 << i) & x) != 0) {
                mk.flavors[mk.n] = flavors[i];
                mk.n++;
            }
        }
        return mk;
    }
}
