
package iteratoruk.fizzbuzz;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Fizz {

    public static String buzz(int start, int end) {
        return IntStream
                .range(start, end + 1)
                .mapToObj(i -> (int) i)
                .map(i -> i % 15 == 0 ? "fizzbuzz" : i % 5 == 0 ? "buzz" : i % 3 == 0 ? "fizz" : Integer.valueOf(i).toString())
                .collect(Collectors.joining(" "));
    }

    private Fizz(int x, int y) {}

}
