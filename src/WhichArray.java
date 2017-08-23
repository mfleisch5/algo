import java.util.ArrayList;

/**
 * Created by michaelfleischmann on 8/17/17.
 */
public class WhichArray {
    public static void main(String[] args) {
        int MILL = 10000000;
        ArrayList<Integer> list = new ArrayList<>();
        long startList = System.nanoTime();
        for (int i = 0; i < MILL; i++) {
            list.add(0);
        }
        long endList = System.nanoTime();
        System.out.println((endList - startList) / MILL);
        int[] arr = new int[MILL];
        long startArray = System.nanoTime();
        for (int i = 0; i < MILL; i++) {
            arr[i] = 0;
        }
        long endArray = System.nanoTime();
        System.out.println((endArray - startArray) / MILL);


    }
}
