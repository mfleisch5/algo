import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by michaelfleischmann on 7/9/17.
 */
public class Exponential {
    private static int sumAdd(List<Integer> list, int added) {
        for(int i : list) {
            added += i;
        }
        return added;
    }

    private static List<Integer> listAdd(Integer add, List<Integer> list) {
        List<Integer> res = new LinkedList<>();
        for(Integer li : list) {
            res.add(li + add);
        }
        return res;
    }

    private static List<Integer> sumLists(List<Integer> list) {
        List<Integer> res = new LinkedList<>();
        if(list.isEmpty()) {
            return res;
        }
        else {
            Integer first = list.get(0);
            List<Integer> sub = list.subList(1, list.size());
            res.addAll(sumLists(sub));
            res.add(sumAdd(sub, 0));
            res.addAll(listAdd(first, sumLists(sub)));
            return res;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>();
        Scanner scanning = new Scanner(System.in);
        while (scanning.hasNextLine()) {
            String st = scanning.nextLine();
            int i = new BigInteger(st).intValue();
            l.add(i);
        }
        List<Integer> sums = sumLists(l);
        sums.add(sumAdd(l, 0));
        for(int sum: sums) {
            System.out.println(sum);
        }
    }
}