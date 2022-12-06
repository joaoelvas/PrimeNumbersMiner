package com.example.primenumbersminer;

import com.example.primenumbersminer.model.Number;

public class Mocks {

    public static final Number PRIME_NUMBER_UNPROCESSED = Number.builder()
            .integer(2)
            .build();

    public static final Number PRIME_NUMBER_PROCESSED = Number.builder()
            .id(0)
            .integer(2)
            .isPrime("true")
            .build();

}
