import java.math.BigInteger;
import java.util.Scanner;

// BigSum:   Just sums the numbers given as input.
// Linear in the size of the input.
// Also, a demo for how to use Scanner and BigInteger.
public class BigSum {
    public static void main(String[] args) {
        BigInteger sum = BigInteger.ZERO;
        Scanner myScanner = new Scanner(System.in);
        while(myScanner.hasNextLine()) {
            String x = myScanner.nextLine();
            if (x.equals(";")) {
                break;
            } else {
                BigInteger i = new BigInteger(x);
                sum = sum.add(i);
            }
        }
        System.out.println(sum);
    }
}