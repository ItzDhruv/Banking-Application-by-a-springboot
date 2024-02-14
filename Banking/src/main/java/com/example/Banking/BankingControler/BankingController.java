package com.example.Banking.BankingControler;

import com.example.Banking.BankingService.BankingService;
import com.example.Banking.Model.BankingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bank")
public class BankingController {
    @Autowired
    BankingService bankingService;
    @PostMapping("/create")
    public BankingModel openAccount(@RequestBody BankingModel bankingModel)
    {
        return bankingService.createAccount(bankingModel);
    }
    @GetMapping("/getAllDetails")
    public List<BankingModel> getAllDetails()
    {
        return bankingService.getAllDetails();
    }

    @GetMapping("/getById/{accountNo}")
    public Optional<BankingModel> getById(@PathVariable long accountNo)
    {
        return bankingService.getById(accountNo);
    }

    @PutMapping("/withdraw")
    public BankingModel withdraw(@RequestParam long accountNo,double balance)
    {
        return bankingService.withdraw(accountNo,balance);
    }

    @PutMapping("/deposite")
    public BankingModel deposite(@RequestParam long accountNo,double balance){
        return bankingService.deposite(accountNo,balance);
    }

    @PutMapping("/transfer")
    public BankingModel transferMoney(@RequestParam long accountNo1,long accountNo2,double balance)  {
         return bankingService.transferMoney(accountNo1,accountNo2,balance);
    }
    @PutMapping("/delete")
    public String deleteAccount(@RequestParam long accountNo)
    {
       return bankingService.delteAccoount(accountNo);
    }
}
