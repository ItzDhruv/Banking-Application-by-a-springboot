package com.example.Banking.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
@Table(name = "bank_details")
public class BankingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNo;
    private String name;
    private double balance;
}
