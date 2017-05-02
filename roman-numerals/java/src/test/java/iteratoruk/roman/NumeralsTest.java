package iteratoruk.roman;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumeralsTest {

    @Test
    public void shouldConvertOne() {
        assertThat(Numerals.toDecimal("I")).isEqualTo(1L);
    }

    @Test
    public void shouldConvertFive() {
        assertThat(Numerals.toDecimal("V")).isEqualTo(5L);
    }

    @Test
    public void shouldConvertTen() {
        assertThat(Numerals.toDecimal("X")).isEqualTo(10L);
    }

    @Test
    public void shouldConvertFifty() {
        assertThat(Numerals.toDecimal("L")).isEqualTo(50L);
    }

    @Test
    public void shouldConvertOneHundred() {
        assertThat(Numerals.toDecimal("C")).isEqualTo(100L);
    }

    @Test
    public void shouldConvertFiveHundred() {
        assertThat(Numerals.toDecimal("D")).isEqualTo(500L);
    }

    @Test
    public void shouldConvertOneThousand() {
        assertThat(Numerals.toDecimal("M")).isEqualTo(1000L);
    }

    @Test
    public void shouldConvertRepresentativeComplexNumber() {
        assertThat(Numerals.toDecimal("MDCCXCVI")).isEqualTo(1796);
    }

}