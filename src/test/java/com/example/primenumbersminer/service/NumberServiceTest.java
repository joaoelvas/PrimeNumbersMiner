package com.example.primenumbersminer.service;

import com.example.primenumbersminer.repository.NumbersRepository;
import com.example.primenumbersminer.validation.IsPrimeNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;

import static com.example.primenumbersminer.Mocks.PRIME_NUMBER_PROCESSED;
import static com.example.primenumbersminer.Mocks.PRIME_NUMBER_UNPROCESSED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
class NumberServiceTest {

    @InjectMocks
    NumberService cut;

    @Mock
    NumbersRepository numbersRepository;

    @Mock
    IsPrimeNumber isPrimeNumber;

    @Test
    void givenANonProcessedNumber_thenReturnNumber() throws InterruptedException, ExecutionException {
        when(isPrimeNumber.test(anyLong())).thenReturn(Boolean.TRUE);
        when(numbersRepository.save(any())).thenReturn(PRIME_NUMBER_PROCESSED);

        var n = cut.processNumber(PRIME_NUMBER_UNPROCESSED).get();

        assertThat(n.getIsPrime()).isEqualTo(PRIME_NUMBER_PROCESSED.getIsPrime());
    }
}