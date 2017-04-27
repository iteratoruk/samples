
package iteratoruk.fizzbuzz;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzTest {

    @Test
    public void shouldReturnFizzBuzzStringGivenRangeOneToTwenty() throws Exception {
        assertThat(Fizz.buzz(1, 20)).isEqualTo("1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz");
    }

}
