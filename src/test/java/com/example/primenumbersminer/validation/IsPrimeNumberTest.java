package com.example.primenumbersminer.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.example.primenumbersminer.validation.IsPrimeNumber.isPrimeNumber;
import static org.assertj.core.api.Assertions.assertThat;

class IsPrimeNumberTest {

    @MethodSource("primeNumbersProvider")
    @ParameterizedTest(name = "#{index} - Run test with number {0}")
    void givenAPrimeNumber_thenReturnTrue(long number) {
        assertThat(isPrimeNumber().test(number)).isTrue();
    }

    @MethodSource("nonPrimeNumbersProvider")
    @ParameterizedTest(name = "#{index} - Run test with number {0}")
    void givenAPrimeNumber_thenReturnFalse(long number) {
        assertThat(isPrimeNumber().test(number)).isFalse();
    }

    static Stream<Integer> primeNumbersProvider() {
        return Stream.of(
                2, 3, 5, 7, 11, 13, 17, 19, 23
        );
    }

    static Stream<Integer> nonPrimeNumbersProvider() {
        return Stream.of(
                1, 4, 6, 8, 10, 12, 14, 15, 16
        );
    }

}