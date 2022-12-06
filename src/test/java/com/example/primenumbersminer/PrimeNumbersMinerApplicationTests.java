package com.example.primenumbersminer;

import com.example.primenumbersminer.model.Number;
import com.example.primenumbersminer.repository.NumbersRepository;
import com.example.primenumbersminer.service.NumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static com.example.primenumbersminer.Mocks.PRIME_NUMBER_UNPROCESSED;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = { PrimeNumbersMinerApplication.class }
)
class PrimeNumbersMinerIntegrationTests implements DatabaseContainerInitializer {

    @Autowired
    NumberService service;

    @Autowired
    NumbersRepository repository;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        DatabaseContainerInitializer.configure(registry);
    }

    @Test
    void testProcessNumber() throws InterruptedException {
        Number number = service.create(PRIME_NUMBER_UNPROCESSED);
        Thread.sleep(3000);
        assertThat(repository.findById(number.getId()).get().getIsPrime()).isEqualTo("true");
    }




}
