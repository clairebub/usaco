import java.io.*;
import java.util.*;

public class Exercise {

    public static void main(String[] args) throws Exception {

        Scanner fin = new Scanner(new File("exercise.in"));
        int N = fin.nextInt();
        int M = fin.nextInt();

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();
        helper(1, N, 0, combo, result );
        HashSet<Integer> seen = new HashSet<>();
        int total = 0;
        for (List<Integer> xx : result) {
            int lcm = 1;
            for (Integer x : xx) {
                lcm = lcm(lcm, x);
            }
            if (seen.contains(lcm)) {
                continue;
            } else {
                seen.add(lcm);
                total = (total + lcm) % M;
            }

        }
        PrintWriter pw = new PrintWriter(new File("exercise.out"));
        pw.println(total);
        pw.close();
    }



    static void helper(int start, int target, int sum,
                        List<Integer> list, List<List<Integer>> result){
        if(sum>target){
            return;
        }

        if(sum==target){
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i <= target; i++) {
            list.add(i);
            helper(i, target, sum + i, list, result);
            list.remove(list.size() - 1);
        }
    }

    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static int lcm(int a, int b)
    {
        return (a*b)/gcd(a, b);
    }
}
