package iteratoruk.numbers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberFormatterTest {

    @Test
    public void shouldFormatZero() {
        assertThat(NumberFormatter.format(0)).isEqualTo("zero");
    }

    @Test
    public void shouldHandleOne() {
        assertThat(NumberFormatter.format(1)).isEqualTo("one");
    }

    @Test
    public void shouldHandleFourteenThousandSevenHundredAndNinetyTwo() {
        assertThat(NumberFormatter.format(14792)).isEqualTo("fourteen thousand seven hundred and ninety two");
    }

    @Test
    public void shouldHandleOneHundred() {
        assertThat(NumberFormatter.format(100)).isEqualTo("one hundred");
    }

    @Test
    public void shouldHandleTrillions() {
        assertThat(NumberFormatter.format(469358367245123L))
                .isEqualTo("four hundred and sixty nine trillion three hundred and fifty eight billion three hundred and sixty seven million two hundred and forty five thousand one hundred and twenty three");
    }

}