import java.io.*;
import java.util.*;

//
// Take as input of lines such as:
// Erika 3.4 5.6 7.9 0
// Mika 4 5 31 31
// Duba 34 42 4 4
// Each line has 5 fields. The first is the name of a rectangle. The 2nd, 3rd is the (x, y) coordinates of
// the top left corner of a rectangle. The 4th, 5th is the (x, y) coordinates of the bottom right corner of
// the rectangle.
//
// Please find the name of the rectangle covers the most area and its the name and the area.
public class YouAreCovered {
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("cover.in"));
        PrintWriter fout = new PrintWriter(System.out);
        HashMap<String, ArrayList<Double>> nah = new HashMap();
        while (fin.hasNextLine()){
            String line = fin.nextLine();
            Scanner wutde = new Scanner(line);
            String name = wutde.next();
            ArrayList<Double> helloo = new ArrayList<Double>();
            while(wutde.hasNext()){
                helloo.add(wutde.nextDouble());
            }
            nah.put(name, helloo);
        }
        double maxi = Double.MIN_VALUE;
        String maximans = null;
        for (String k : nah.keySet()) {
            ArrayList<Double> v = nah.get(k);
            double area = 1;
            area *= (v.get(0)-v.get(2));
            area *= (v.get(1)-v.get(3));
            area = Math.abs(area);
            fout.println(area);
            if (area > maxi){
                maxi = area;
                maximans = k;
            }
        }
        fout.print(maximans);
        fout.print(" ");
        fout.println(maxi);
        fout.println(maximans + ": " + maxi);
        fout.close();
    }
}
