package com.example.Banking.BankingService;

import com.example.Banking.BankingRepositry.BankingRepo;
import com.example.Banking.Model.BankingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BankingService {
    @Autowired
    BankingRepo bankingRepo;

    public BankingModel createAccount(BankingModel bankingModel) {

        return bankingRepo.save(bankingModel);
    }

    public List<BankingModel> getAllDetails() {
        return bankingRepo.findAll();
    }

    public Optional<BankingModel> getById(long accountNo) {
        return bankingRepo.findById(accountNo);
    }

    public BankingModel withdraw(long accountNo, double balance) {
        try {

            BankingModel list = bankingRepo.findById(accountNo).orElseThrow(()-> new RuntimeException("Account not found"));
            double oldMoney = list.getBalance();
            if (oldMoney >= balance) {
                list.setBalance(oldMoney - balance);
            } else {
                System.out.println("balance is not enough");
            }
            return bankingRepo.save(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing withdrawal");
        }
    }

    public BankingModel deposite(long accountNo, double balance) {
        try {
            BankingModel bankingModel = bankingRepo.findById(accountNo).get();
            double newBalance = balance + bankingModel.getBalance();
            bankingModel.setBalance(newBalance);
            return bankingRepo.save(bankingModel);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error processing Deposite");
        }
    }

    public BankingModel transferMoney(long userAccount, long targetAcc, double balance) {
        BankingModel bankingModel1 = bankingRepo.findById(userAccount).get();
        BankingModel bankingModel2 = bankingRepo.findById(targetAcc).get();
      if (bankingModel1.getBalance()>=balance)
      {
        withdraw(bankingModel1.getAccountNo(),balance);
        deposite(bankingModel2.getAccountNo(),balance);
      }
      else {
          System.out.println("Not enough balance");
      }
return bankingModel1;
    }

    public String delteAccoount(long accountNo) {
        BankingModel bankingModel = bankingRepo.findById(accountNo).get();
        bankingRepo.delete(bankingModel);
        return "Delete account no : "+accountNo+" succesfully";
    }
}
