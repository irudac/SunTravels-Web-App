package com.suntravels.backend.repository;

import com.suntravels.backend.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long>
{
    @Query("SELECT c FROM Contract c WHERE c.validFrom <= :checkIn AND c.validUntil >= :checkOut")
    List<Contract> searchValidContractWithinDuration( @Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);

}

