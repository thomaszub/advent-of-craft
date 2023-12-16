package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzzTests {
    @ParameterizedTest
    @ValueSource(ints = {1, 67, 82})
    void returns_the_given_number(int number) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(number))
                .isEqualTo(String.valueOf(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 66, 99})
    void returns_Fizz(int number) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(number))
                .isEqualTo("Fizz");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 50, 85})
    void returns_Buzz(int number) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(number))
                .isEqualTo("Buzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void returns_FizzBuzz(int number) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(number))
                .isEqualTo("FizzBuzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 101})
    void throws_an_exception(int number) {
        assertThatThrownBy(() -> FizzBuzz.convert(number))
                .isInstanceOf(OutOfRangeException.class);
    }
}