package net.enset.ebankservice.feign;


import net.enset.ebankservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// On indique le nom exact du service tel qu'il est enregistré dans Eureka
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    // On définit la méthode HTTP et le chemin de l'API du Customer Service
    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable Long id);
}