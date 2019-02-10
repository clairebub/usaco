import java.io.*;
import java.util.*;

public class Jan19_Poetry {
    static long[] dp;

    public static void main(String[] args) throws Exception {
        run(args.length == 0 ? 0 : Integer.parseInt(args[0]));
    }
    public static void run(int ii) throws Exception {

        int N_words, M_lines, K_syllables;
        String fin_name = String.format("data/poetry_gold_jan19/%d.in", ii);
        String fout_name = String.format("data/poetry_gold_jan19/s_%d.out", ii);
        BufferedReader br = new BufferedReader(new FileReader(fin_name));
        PrintWriter pw = new PrintWriter(new File(fout_name));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N_words = Integer.parseInt(st.nextToken());
        M_lines = Integer.parseInt(st.nextToken());
        K_syllables = Integer.parseInt(st.nextToken());

        // we keep track the following
        int word_syllables[] = new int[N_words];
        int word_class[] = new int[N_words];
        HashMap<Integer, HashSet<Integer>> class_2_words = new HashMap<>();
        HashMap<Integer, Integer> syllable_wc = new HashMap<>();

        for (int i = 0; i < N_words; i++) {
            st = new StringTokenizer(br.readLine());
            int num_syl = Integer.parseInt(st.nextToken());
            int cls = Integer.parseInt(st.nextToken());
            word_syllables[i] = num_syl;
            word_class[i] = cls;

            if (syllable_wc.containsKey(num_syl)) {
                syllable_wc.put(num_syl, syllable_wc.get(num_syl) + 1);
            } else {
                syllable_wc.put(num_syl, 1);
            }
            if (!class_2_words.containsKey(cls)) {
                class_2_words.put(cls, new HashSet<>());
            }
            class_2_words.get(cls).add(i);
        }

        // num_of_ways2(K_syllables, syllable_wc);
        // ways for a line to end with a class
        dp = new long[K_syllables+1];
        Arrays.fill(dp, -1);
        HashMap<Integer, Long> class_ways = new HashMap<>();
        for (int cls : class_2_words.keySet()) {
            long sum = 0;
            for (int w : class_2_words.get(cls)) {
//                sum += dp[K_syllables - word_syllables[w]];
                long a = num_of_ways2(K_syllables - word_syllables[w], syllable_wc);
                sum = (sum + a) % 1000000007;
                check_overflow("67", sum);
            }
            class_ways.put(cls, sum);
        }

        HashMap<String, Integer> rhyme_linesCount = new HashMap<>();
        for (int i = 0; i < M_lines; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            if (rhyme_linesCount.containsKey(a)) {
                rhyme_linesCount.put(a, rhyme_linesCount.get(a) + 1);
            } else {
                rhyme_linesCount.put(a, 1);
            }
        }
        long total = 1;
        for (String r : rhyme_linesCount.keySet()) {
            long r_ways = 0; // contributions from this rhyme
            int lines = rhyme_linesCount.get(r);
            for (int cls : class_ways.keySet()) {
                long ways_from_cls = 1;
                long ways = class_ways.get(cls);
                for (int i = 0; i < lines; i++) {
                    ways_from_cls =  (ways_from_cls * ways) % 1000000007;
                }
                r_ways = (r_ways + ways_from_cls) % 1000000007;
                check_overflow("94", r_ways);
            }
            total = total * r_ways % 1000000007;
        }

        System.out.println("total=" + total);
        pw.println(total);
        pw.flush();
        pw.close();
    }

    static long num_of_ways2(int K, HashMap<Integer, Integer> syllable_length_to_count) {
        if (dp[K] >= 0) {
            return dp[K];
        }
        for (int i = 1; i <= K; i++) {
            long sum = 0;
            for (int j = 1; j < i; j++) {
                if (dp[j] == 0 || dp[i-j] == 0) {
                    continue;
                }
                long a =  (dp[j]) * (dp[i-j]) % 1000000007;
                String m = String.format("i=%d, j=%d, i-j=%d, dp[j]=%d, dp[i-j]=%d, a=%d", i, j, i-j, dp[j], dp[i-j], a);
                check_overflow(m, a);
                sum = (sum + a) % 1000000007;
            }
            sum += syllable_length_to_count.containsKey(i) ? syllable_length_to_count.get(i) : 0;
            sum = sum % 1000000007;
            dp[i] = sum;
        }
        return dp[K];
    }
/*
    // number of unique ways for K syllables
    static long num_of_ways(int num_s, HashMap<Integer, Integer> syllable_length_to_count) {
        if (dp[num_s] >= 0) {
            return dp[num_s];
        }
        long sum = 0;
        for (int len : syllable_length_to_count.keySet()) {
            int c = syllable_length_to_count.get(len);
            if (len == num_s) {
                sum += c;
            } else if (len < num_s) {
                long a = c * num_of_ways(num_s - len, syllable_length_to_count) % 1000000007;
                sum = (sum + a) % 1000000007;
            }
            sum = sum % 1000000007;
        }
        dp[num_s] = sum;
        return dp[num_s];
    }
*/
    static void check_overflow(String marker, long n) {
        if (n < 0) {
            System.out.println(marker + " overflow: " + n);
        }
    }
}
