import java.io.*;
import java.util.*;

public class Help {
    static class interval{
        int a;
        int b;
        interval(int hi, int bye){
            a = hi;
            b = bye;
        }
    }
    static int N;
    static int n;
    public static void main(String[] args) throws Exception{
        Scanner fin = new Scanner(new File("help.in"));
        PrintWriter pw = new PrintWriter(new File("help.out"));
        // debug outputs
        // pw = new PrintWriter(System.out);

        N = fin.nextInt();
        n = 0;
        ArrayList<interval> input = new ArrayList<>();
        for(int i = 0; i < N; i++){
            int start = fin.nextInt() - 1;
            int end = fin.nextInt() - 1;
            input.add(new interval(start, end));
            n = Math.max(n, end);
        }
        int[] lol = new int[N];
        for(int i = 0; i < N; i++){
            lol[i] = i;
        }
        n++;
        int answer = 0;
        int[] all = sweepline(input);
        ArrayList<ArrayList<Integer>> indexes = subsets(lol);
        //System.out.println(indexes.get(0).size());
        for (ArrayList<Integer> i : indexes) {
            ArrayList<interval> intervals = new ArrayList<>();
            for (int j : i) {
                //    System.out.println(j + " " );
                intervals.add(input.get(j));
            }
            ArrayList<Integer> union = union(intervals);
            if (union.size() == 0) continue;
            int min = all[union.get(0)];
            for (int j : union) {
                min = Math.min(all[j], min);
            }

            answer += Math.min(min, i.size());
        }
        pw.println(answer);
        pw.close();
    }
    static ArrayList<Integer> union(ArrayList<interval> a){
        ArrayList<Integer> answer = new ArrayList<>();
        int[] temp = sweepline(a);
        for(int i = 0; i < n; i++){
            if(temp[i] > 0){
                answer.add(i);
            }
        }
        return answer;
    }

    static int[] sweepline(ArrayList<interval> intervals){
        int[] plusminus = new int[n];
        int[] numreigons = new int[n];
        for(interval i : intervals){
            plusminus[i.a]++;
            plusminus[i.b]--;
        }
        int total = 0;
        for(int i = 0; i < n; i++){
            total += plusminus[i];
            numreigons[i] = total;
        }
        return numreigons;
    }
    static ArrayList<ArrayList<Integer>> subsets(int[] S) {
        if (S == null)
            return null;

        Arrays.sort(S);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < S.length; i++) {
            ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
            for (ArrayList<Integer> a : result) {
                temp.add(new ArrayList<Integer>(a));
            }
            for (ArrayList<Integer> a : temp) {
                a.add(S[i]);
            }
            ArrayList<Integer> single = new ArrayList<Integer>();
            single.add(S[i]);
            temp.add(single);
            result.addAll(temp);
        }
        result.add(new ArrayList<Integer>());
        return result;
    }
}
