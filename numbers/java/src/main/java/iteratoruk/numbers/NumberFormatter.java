
package iteratoruk.numbers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class NumberFormatter {

    private static String[] SMALL = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static String[] TENS = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static String[] SCALE = {
            "", "thousand", "million", "billion", "trillion"
    };

    public static String format(long i) {
        // 0. handle zero
        if (i == 0) return "zero";
        // 1. chunk into hundred-based groups
        List<Integer> groups = chunk(i);
        // 2. go backwards through the chunks and describe each
        StringBuilder sb = new StringBuilder();
        for (int idx = groups.size() - 1; idx >= 0; idx--) {
            sb = sb.append(describe(groups.get(idx)));
            // 3. append a SCALE descriptor where necessary
            if (idx > 0) {
                sb = sb.append(" ").append(SCALE[idx]).append(" ");
            }
        }
        // 4. remove and flab and return
        return sb.toString().trim();
    }

    // max 999
    private static String describe(int i) {
        int hundredUnits = i / 100;
        int tensUnits = i % 100;
        StringBuilder sb = new StringBuilder();
        if (hundredUnits > 0) {
            sb = sb.append(SMALL[hundredUnits]).append(" hundred");
            if (tensUnits != 0) {
                sb = sb.append(" and ");
            }
        }
        int tens = tensUnits / 10;
        int units = tensUnits % 10;
        if (tens > 1) {
            sb = sb.append(TENS[tens]);
            if (units != 0) {
                sb.append(" ").append(SMALL[units]);
            }
        } else if (tensUnits != 0) {
            sb = sb.append(SMALL[tensUnits]);
        }
        return sb.toString();
    }

    // group into a list of numbers where element 0 is "hundreds" (0-999), 1 is "thousands", 2 "millions" and so on
    private static List<Integer> chunk(long i) {
        // 1. turn the int into a string
        String str = String.valueOf(i);
        // 2. reverse the string
        String rev = new StringBuilder(str).reverse().toString();
        // 3. break the string into chunks up to a maximum of 3 characters in length
        List<Character> strs = rev.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        int chunkSize = 3;
        return IntStream
                .range(0, strs.size() / chunkSize + 1)
                .mapToObj(num -> strs.subList(num *= chunkSize, strs.size() - chunkSize >= num ? num + chunkSize : strs.size()))
                .map(chararacters -> chararacters.stream().map(character -> new StringBuilder(character.toString())).collect(Collectors.joining()))
                // 4. remove any empty strings
                .filter(s -> s != null && s.length() > 0)
                // 5. reverse each chunk string back
                .map(s -> new StringBuilder(s).reverse().toString())
                // 6. convert each chunk to an integer
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }

    private NumberFormatter() {}
}
