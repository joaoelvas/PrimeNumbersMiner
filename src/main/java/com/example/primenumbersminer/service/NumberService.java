package com.example.primenumbersminer.service;

import com.example.primenumbersminer.validation.IsPrimeNumber;
import com.example.primenumbersminer.model.Number;
import com.example.primenumbersminer.repository.NumbersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class NumberService {

    NumbersRepository numbersRepository;
    IsPrimeNumber isPrimeNumber;

    public Number create(Number number) {
        return numbersRepository.save(number);
    }

    public Page getNumbers(Pageable pageable) {
        return numbersRepository.findAllByIsPrimeNull(pageable);
    }

    @Async
    public CompletableFuture<Number> processNumber(Number number) throws InterruptedException {
        log.debug(Thread.currentThread().getName() + " - Grabbing number: " + number.toString());

        number.setIsPrime(
                isPrimeNumber.test(
                        number.getInteger()) ? "true" : "not"
        );
        var savedNumber = numbersRepository.save(number);
        return CompletableFuture.completedFuture(savedNumber);
    }
}
