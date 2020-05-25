import java.io.*;
import java.util.*;
//
// The input is like the following
// John
// 12 34 53
// Mike
// 12 4 5
// Basically, it's a line for a person's name, followed by a line including the amount of moneys he collected
//
// Please write a program to read from the input file rop.in and print the people with most and least amount of
// money together with the amount they have
public class RichOrPoor {
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("rop.in"));
        PrintWriter fout = new PrintWriter(System.out);
        HashMap<String, Long> personMoney = new HashMap<String, Long>();
        while(fin.hasNextLine()) {
            String person = fin.nextLine();
            long total = 0;
            String line = fin.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                int amt = lineScanner.nextInt();
                total += amt;
            }
            personMoney.put(person, total);
        }
        long mini = Integer.MAX_VALUE;
        long maxi = Integer.MIN_VALUE;
        String minimans = null;
        String maximans = null;
        for (Map.Entry<String, Long> entry : personMoney.entrySet()) {
            if(entry.getValue()<mini){
                mini = entry.getValue();
                minimans = entry.getKey();
            }
            if(entry.getValue()>maxi){
                maxi = entry.getValue();
                maximans = entry.getKey();
            }
        }
        fout.println(maximans);
        fout.println(minimans);
        fout.close();
    }
}
