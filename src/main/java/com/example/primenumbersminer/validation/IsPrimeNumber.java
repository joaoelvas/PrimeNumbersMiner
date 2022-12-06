package com.example.primenumbersminer.validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class IsPrimeNumber implements Predicate<Long> {

    @Override
    public boolean test(Long aLong) {
        if(aLong <= 1) {
            return false;
        }
        for(int i = 2 ; i <= aLong / 2; i++) {
            if((aLong % i) == 0)
                return  false;
        }
        return true;
    }

    public static Predicate<Long> isPrimeNumber() {
        return new IsPrimeNumber();
    }

}
