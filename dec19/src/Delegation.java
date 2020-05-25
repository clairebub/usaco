import java.io.*;
import java.util.*;

public class Delegation {

    static LinkedList<Integer>[] adj;
    static HashMap<Integer, Integer> leaf_count = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("deleg.in"));
        PrintWriter pw = new PrintWriter(new File("deleg.out"));
        // debug outputs
         pw = new PrintWriter(System.out);

        int N = fin.nextInt();
        int[] answers = new int[N - 1];

        adj= new LinkedList[N];
        for (int i = 0; i < N; i++){
            adj[i] = new LinkedList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            int node1 = fin.nextInt() - 1;
            int node2 = fin.nextInt() - 1;
            adj[node1].add(node2);
            adj[node2].add(node1);
            if (!leaf_count.containsKey(node1)) {
                leaf_count.put(node1, 0);
            }
            leaf_count.put(node1, leaf_count.get(node1) + 1);
            if (!leaf_count.containsKey(node2)) {
                leaf_count.put(node2, 0);
            }
            leaf_count.put(node2, leaf_count.get(node2) + 1);
        }
        int centers = 0;
        for(int i = 0; i < N; i++){
            if(adj[i].size() > 1){
                centers++;
            }
        }
        if(centers <= 1){
            do_star(pw, N);
            //done !!!
        }
        else{
            non_star(pw, N);
        }
        pw.println();
        pw.close();
    }

    static void non_star(PrintWriter pw, int N) {
        // find all the leaf
        for (int k = 1; k < N; k++) {
            if ((N-1) % k != 0) {
                pw.print(0);
                continue;
            }

            if (can_be_partitioned(k)) {
                pw.print(1);
            } else {
                pw.print(0);
            }
        }

    }

    static boolean can_be_partitioned(int k) {
        if (k <= 3) {
            return true;
        }
        else {
            return false;
        }
    }

    static void do_star(PrintWriter pw, int N) {
        for (int k = 1; k < N; k++) {
            if (k == 1) {
                pw.print(1);
                continue;
            }
            if (k == 2 && (N-1) % 2 == 0) {
                pw.print(1);
                continue;
            }
            pw.print(0);
        }
    }
/*
    static void star(PrintWriter pw, int N){
        pw.print(1);
        if((N-1) % 2 == 0){
            pw.print(1);
        }
        else{
            pw.print(0);
        }
        for(int i = 0; i < N - 3; i++){
            pw.print(0);
        }
    } */

    static ArrayList<Integer> factor(int n){
        ArrayList<Integer> factors = new ArrayList<>();
        Stack<Integer> temp = new Stack<>();
        factors.add(1);
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n%i == 0){
                factors.add(i);
                temp.push(n/i);
                //factors.add(n/i);
            }
        }
        while(!temp.isEmpty()){
            factors.add(temp.pop());
        }
        return factors;
    }
}
