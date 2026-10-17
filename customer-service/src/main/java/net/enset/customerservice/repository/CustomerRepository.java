package net.enset.customerservice.repository;

import net.enset.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {
}
