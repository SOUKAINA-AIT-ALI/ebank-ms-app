package net.enset.ebankservice.services;

import net.enset.ebankservice.entities.BankAccount;
import net.enset.ebankservice.feign.CustomerRestClient;
import net.enset.ebankservice.model.Customer;
import net.enset.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {

    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    public List<BankAccount> getAllBankAccounts() {
        return accountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id) {
        BankAccount bankAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        bankAccount.setCustomer(
                customerRestClient.getCustomerById(bankAccount.getCustomerId())
        );
        return bankAccount;
    }

    public BankAccount save(BankAccount bankAccount) {
        try {
            // 1. On essaie de récupérer le client pour vérifier qu'il existe
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());

            // 2. Si pas d'exception, on génère les données et on enregistre le compte
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return accountRepository.save(bankAccount);

        } catch (Exception e) {
            // 3. Si le client n'existe pas (ou problème réseau), on lance une exception
            throw new RuntimeException(e.getMessage());
        }
    }




}