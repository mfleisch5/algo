import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by michaelfleischmann on 8/13/17.
 */
public class ZeroTime {
    public static void main(String[] args) {

        for(int length = 100000; length <= 10000000; length *= 10) {
            ArrayList<Integer> array = new ArrayList<>(Arrays.asList(new Integer[length]));
            long startTime = System.nanoTime();
            array.add(0, 1);
            long endTime = System.nanoTime();
            System.out.println("ArrayList Length " + length + ": " + (endTime - startTime));
        }
        for(int length = 100000; length <= 10000000; length *= 10) {
            LinkedList<Integer> linked = new LinkedList<>(Arrays.asList(new Integer[length]));
            long startTime = System.nanoTime();
            linked.push(1);
            long endTime = System.nanoTime();
            System.out.println("LinkedList Length " + length + ": " + (endTime - startTime));
        }
    }
}
