package iteratoruk.roman;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Numerals {

    private static Map<Character, Long> NUMERALS = Stream.of(
            new SimpleEntry<>('N', 0L),
            new SimpleEntry<>('I', 1L),
            new SimpleEntry<>('V', 5L),
            new SimpleEntry<>('X', 10L),
            new SimpleEntry<>('L', 50L),
            new SimpleEntry<>('C', 100L),
            new SimpleEntry<>('D', 500L),
            new SimpleEntry<>('M', 1000L)
        ).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

    public static long toDecimal(String numerals) {
        // 1. split the string into characters
        // 2. convert each character into the value it represents
        // 3. start from the right (the lowest value numeral)
        // 4. keep a running total; record of the maximum numeral encountered so far
        // 5. for each character:
        //      i.  if character is greater than or equal to the maximum, add it to the running total and update the maximum
        //      ii. else, subtract it from the running total
        long total = 0L;
        long max = 0L;
        List<Long> vals = numerals.chars().mapToObj(c -> (char) c).map(c -> NUMERALS.get(c)).collect(Collectors.toList());
        for (int i = vals.size() - 1; i >=0; i--) {
            long v = vals.get(i);
            if (v >= max) {
                max = v;
                total += v;
            } else {
                total -= v;
            }
        }
        return total;
    }

    private Numerals() {}

}
