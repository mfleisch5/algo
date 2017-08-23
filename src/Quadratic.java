import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by michaelfleischmann on 7/9/17.
 */
public class Quadratic {
    public static void main(String[] args) {
        ArrayList<Integer> in = new ArrayList<>();
        boolean[] lookup = new boolean[10001];
        Scanner scanning = new Scanner(System.in);
        while (scanning.hasNextLine()) {
            String st = scanning.nextLine();
            int i = new BigInteger(st).intValue();
            in.add(i);
            lookup[i] = true;
        }
        HashMap<Integer, ArrayList<Integer>> multiples = new HashMap<>();
        for (int a : in) {
            for (int b : in) {
                int c = a * b;
                if(c > 10000) {
                    continue;
                }
                if(lookup[c] && a != b && b != c && c != a) {
                    ArrayList<Integer> multiple = new ArrayList<>();
                    multiple.add(a);
                    multiple.add(b);
                    Collections.sort(multiple);
                    multiples.put(c, multiple);
                }
            }
        }
        int max = 0;
        for(Integer key : multiples.keySet()) {
            max = Math.max(key, max);
        }
        if(max == 0) {
            System.out.println(0 + "\n" + 0 + "\n" + 0);
        }
        else {
            ArrayList<Integer> mult = multiples.get(max);
            System.out.println(mult.get(0) + "\n" + mult.get(1) + "\n" + max);
        }
    }
}
