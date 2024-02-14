package com.example.Banking.BankingRepositry;

import com.example.Banking.Model.BankingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankingRepo extends JpaRepository<BankingModel,Long> {
}
