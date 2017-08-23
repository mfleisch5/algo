/**
 * Created by michaelfleischmann on 7/6/17.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;

public class Cubic {
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        Scanner scanning = new Scanner(System.in);
        while (scanning.hasNextLine()) {
            String st = scanning.nextLine();
            int i = new BigInteger(st).intValue();
            l.add(i);
        }
        HashMap<Integer, ArrayList<Integer>> multiples = new HashMap<>();
        for (int a : l) {
            for (int b : l) {
                for (int c : l) {
                    if(a * b == c && a != b && b != c && c != a) {
                        ArrayList<Integer> multiple = new ArrayList<>();
                        multiple.add(a);
                        multiple.add(b);
                        Collections.sort(multiple);
                        multiples.put(c, multiple);
                    }
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
