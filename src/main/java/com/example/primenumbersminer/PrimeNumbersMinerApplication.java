package com.example.primenumbersminer;

import com.example.primenumbersminer.model.Number;
import com.example.primenumbersminer.service.NumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@AllArgsConstructor
@Slf4j
@EnableJpaRepositories(basePackages = "com.example.primenumbersminer.repository")
public class PrimeNumbersMinerApplication {

    private static int PAGE_SIZE = 50;

    NumberService numberService;

    public static void main(String[] args) {
        SpringApplication.run(PrimeNumbersMinerApplication.class, args);
    }

    @Scheduled(fixedRate = 1000)
    public void run() {
        log.debug("Started Execution...");

        Page<Number> page = numberService.getNumbers(PageRequest.of(0, PAGE_SIZE));
        page.getContent().stream().forEach(n -> {
            try {
                numberService.processNumber(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        while(page.hasNext()) {
            page = numberService.getNumbers(page.nextPageable());
            page.getContent().stream().forEach(n -> {
                try {
                    numberService.processNumber(n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
