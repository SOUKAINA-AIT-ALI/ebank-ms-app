package net.enset.ebankservice.repository;

import net.enset.ebankservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    // Méthode pour trouver les comptes d'un client spécifique
    List<BankAccount> findByCustomerId(Long customerId);
}

