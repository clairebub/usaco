import java.io.*;
import java.util.*;

public class Sleepy {
    public static void main(String[] args) throws Exception{
        Scanner fin = new Scanner(new File("sleepy.in"));
        int N = fin.nextInt();
        ArrayList<Integer> unsorted = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        for(int i = 0; i < N; i++){
            unsorted.add(fin.nextInt());
        }
        int index = N - 1;
        int prev = unsorted.get(N-1);
        boolean keepgoing = true;
        while(index >= 0 && keepgoing){
            index--;
            if(unsorted.get(index) > prev){
                keepgoing = false;
            }
            prev = unsorted.get(index);
        }
        sorted.addAll(unsorted.subList(index + 1, N));
        /*
        if(index <= N - 1) {
            for (int i = index + 1; i < N; i++) {
                sorted.add(unsorted.get(i));
            }

            for (int i = index + 1; i < N; i++) {
                unsorted.remove(i);
            }
        } */
        PrintWriter fout = new PrintWriter(new File("sleepy.out"));
        int actualsize = unsorted.size() - sorted.size();
        fout.println(actualsize);
        boolean is_first = true;
        while(actualsize > 0){
            int next = unsorted.remove(0);
            actualsize--;
            int insertindex = - Collections.binarySearch(sorted, next) - 1;
            sorted.add(insertindex, next);
            if (!is_first) {
                fout.print(" ");
            }
            fout.print(actualsize + insertindex);
            is_first = false;
        }
        fout.flush();
        fout.close();
    }
}
