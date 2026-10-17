package net.enset.ebankservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.enset.ebankservice.model.Customer;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {
    @Id
    private String id; // utilise un String (UUID)
    private Date createdAt;
    private double balance;
    private String type;
    private Long customerId;

    // @Transient indique à JPA de ne PAS créer cette colonne dans la base de données
    @Transient
    private Customer customer;
}
