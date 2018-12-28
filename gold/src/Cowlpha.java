import java.io.*;
import java.util.*;

public class Cowlpha {
    static HashMap<Character, HashSet<Character>> valid_sequence;
    static int total_valid_words = 0;
    static int modulo = 97654321;
    public static void main(String[] args) throws Exception {
        int U, L, P;

        Scanner fin = new Scanner(new File("cowlpha.in"));
        StringTokenizer st = new StringTokenizer(fin.nextLine());
        U = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        valid_sequence = new HashMap<>();
        for (int i = 0; i < P; i++) {
            String word = fin.nextLine();
            if (!valid_sequence.containsKey(word.charAt(0))) {
                valid_sequence.put(word.charAt(0), new HashSet<>());
            }
            valid_sequence.get(word.charAt(0)).add(word.charAt(1));
        }
        System.out.println(valid_sequence);
        is_valid_words(valid_sequence.keySet(), U, L);
        System.out.println(total_valid_words);
    }

    static void is_valid_words(Set<Character> starting_set, int u, int l) {
        if (u == 0 && l == 0) {
            total_valid_words += 1;
        }
        if (starting_set.isEmpty()) {
            return;
        }
        int x = 0;
        for (Character ch : starting_set) {
            if (Character.isUpperCase(ch) && u > 0) {
                is_valid_words(valid_sequence.get(ch), u-1, l);

            } else if (Character.isLowerCase(ch) && l > 0) {
                is_valid_words(valid_sequence.get(ch), u, l-1);
            }
        }
        return;
    }
}
