import java.io.*;
import java.util.*;

public class Poetry {
    static int[] dp;
    public static void main(String[] args) throws Exception {
        int N_words, M_lines, K_syllables;
        Scanner fin = new Scanner(new File("poetry.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        N_words = Integer.parseInt(st.nextToken());
        M_lines = Integer.parseInt(st.nextToken());
        K_syllables = Integer.parseInt(st.nextToken());
        dp = new int[K_syllables+1];
        Arrays.fill(dp, -1);

        int word_syllables[] = new int[N_words];
        int word_rhymes[] = new int[N_words];
        HashMap<Integer, HashSet<Integer>> class_members = new HashMap<>();
        HashMap<Integer, Integer> si_counts = new HashMap<>();

        for (int i = 0; i < N_words; i++) {
            st = new StringTokenizer(fin.nextLine());
            int si = Integer.parseInt(st.nextToken());
            int ci = Integer.parseInt(st.nextToken());
            word_syllables[i] = si;
            word_rhymes[i] = ci;
            int si_count = 0;
            if (si_counts.containsKey(si)) {
                si_count = si_counts.get(si);
            }
            si_count += 1;
            si_counts.put(si, si_count);
            if (!class_members.containsKey(ci)) {
                class_members.put(ci, new HashSet<Integer>());
            }
            class_members.get(ci).add(i);
        }
        HashMap<String, Integer> poetry_rhymes = new HashMap<>();
        for (int i = 0; i < M_lines; i++) {
            st = new StringTokenizer(fin.nextLine());
            int num_lines = 0;
            String a = st.nextToken();
            if (poetry_rhymes.containsKey(a)) {
                num_lines = poetry_rhymes.get(a);
            }
            num_lines += 1;
            poetry_rhymes.put(a, num_lines);
        }
        // we need to know for each rhyme class, the list of words for that rhyme class
        // then, we need to know the number of ways per rhyme class
//        System.out.println("class_members: " + class_members);
        HashMap<Integer, Integer> class_ways = new HashMap<>();
        for (int ci : class_members.keySet()) {
            int ways = 0;
            for (int w : class_members.get(ci)) {
                int a = word_syllables[w];
                int x = num_of_ways(K_syllables - a, si_counts);
                ways += x;
            }
            class_ways.put(ci, ways);
        }
        int total = 1;
        for (String r : poetry_rhymes.keySet()) {
            int line_count = poetry_rhymes.get(r);
            // how many ways for this rhyme
            int x = 0;
            for (int c : class_ways.keySet()) {
                int w = class_ways.get(c);
                int t = 1;
                for (int i = 0; i < line_count; i++) {
                    t *= w;
                    t = t % 1000000007;
                }
                x += t;
            }
            total *= x;
            total = total % 1000000007;
        }

        //
        PrintWriter fout = new PrintWriter(new File("poetry.out"));
        //PrintWriter fout = new PrintWriter(System.out);
     //   fout.println(String.format("N=%d, M=%d, K=%d", N_words, M_lines, K_syllables));
     //   fout.println(Arrays.toString(word_syllables));
     //   fout.println(Arrays.toString(word_rhymes));
        fout.println(total);

        fout.flush();
        fout.close();
    }

    // number of unique ways to form a single line of K syllables
    static int num_of_ways(int syllables, HashMap<Integer, Integer> syllable_counts) {
        // go through each words,
        if (dp[syllables] >= 0) {
            return dp[syllables];
        }
        int sum = 0;
        for (int num_syllables : syllable_counts.keySet()) {
            if (num_syllables == syllables) {
                sum += syllable_counts.get(num_syllables);
            } else if (num_syllables < syllables) {
                sum += num_of_ways(syllables - num_syllables, syllable_counts) * syllable_counts.get(num_syllables);
            } else {
                // not fit any more
            }
        }
        dp[syllables] = sum;
//        System.out.println("dp[" + syllables + "] = " + sum);
        return dp[syllables];
    }
}
