package com.example.primenumbersminer.repository;

import com.example.primenumbersminer.model.Number;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumbersRepository extends JpaRepository<Number, Long> {

    Page<Number> findAllByIsPrimeNull(Pageable pageable);

}
