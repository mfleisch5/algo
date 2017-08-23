import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by michaelfleischmann on 8/11/17.
 */

class PythonicTuple implements Comparable<PythonicTuple> {
    private long time;
    private Integer pos;

    PythonicTuple(long time, Integer pos) {
        this.time = time;
        this.pos = pos;
    }


    @Override
    public int compareTo(PythonicTuple o) {
        return Long.compare(o.time, this.time);
    }

    @Override
    public String toString() {
        return pos + "," + time + "," + time / pos;
    }

    Integer getPos() {
        return pos;
    }
}
public class ArrayTime {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<PythonicTuple> times = new ArrayList<>();
        for(int i = 0; i < 10000000; i++) {
            long startAdd = System.nanoTime();
            list.add(0);
            long endAdd = System.nanoTime();
            times.add(new PythonicTuple(endAdd - startAdd, i));

        }
        System.out.println("ok");
        Collections.sort(times);
        FileWriter csv = new FileWriter("ArrayTime.csv");
        csv.write("Index,Time,Ratio\n");
        int i = 0;
        while(i < 20) {
            if(times.get(i).getPos() > 1000) {
                csv.write(times.get(i).toString() + "\n");
                i++;
            }
        }
        csv.close();

    }
}
