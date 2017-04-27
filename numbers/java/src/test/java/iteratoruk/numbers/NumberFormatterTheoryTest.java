
package iteratoruk.numbers;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Theories.class)
public class NumberFormatterTheoryTest {

    @DataPoints
    public static int[] numbers = range(0, 1001);

    private static int[] range(int start, int end) {
        int size = end - start;
        int n = start;
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = n;
            n++;
        }
        return numbers;
    }

    private static String[] small = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static String[] scale = {
            "", "thousand", "million", "billion"
    };

    @Theory
    public void shouldTransformIntegerToWord(int i) {
        String expected = null;
        if (i < 20) {
            expected = small[i];
        }
        List<Integer> ints = String.valueOf(i).chars().mapToObj(in -> (char) in).map(c -> Integer.parseInt(c.toString())).collect(Collectors.toList());
        if (i > 19 && i < 100) {
            expected = tens[ints.get(0)];
            if (ints.get(1) > 0) {
                expected += " " + NumberFormatterTheoryTest.small[ints.get(1)];
            }
        }
        if (i > 99 && i < 1000) {
            expected = NumberFormatterTheoryTest.small[ints.get(0)] + " hundred";
            int sub = Integer.parseInt(String.valueOf(ints.get(1) + String.valueOf(ints.get(2))));
            List<Integer> subs = String.valueOf(sub).chars().mapToObj(in -> (char) in).map(c -> Integer.parseInt(c.toString())).collect(Collectors.toList());
            if (sub > 0 && sub < 20) {
                expected += " and " + small[sub];
            } else if (sub > 19) {
                expected += " and " + tens[subs.get(0)];
                if (subs.get(1) > 0) {
                    expected += " " + NumberFormatterTheoryTest.small[subs.get(1)];
                }
            }
        }
        if (i == 1000) {
            expected = "one thousand";
        }
        String actual = NumberFormatter.format(i);
//        System.out.println(i + " =[expected]= " + expected);
//        System.out.println(i + " = [actual] = " + actual);
        assertThat(actual).isEqualTo(expected);
    }

}