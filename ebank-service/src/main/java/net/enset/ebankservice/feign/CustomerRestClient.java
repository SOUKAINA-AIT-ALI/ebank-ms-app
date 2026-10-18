package net.enset.ebankservice.feign;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.enset.ebankservice.model.Customer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// On indique le nom exact du service tel qu'il est enregistré dans Eureka
@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name="customerService" , fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable Long id);


    default Customer getDefaultCustomer(Long id,Exception e){
        return new Customer(id,"Not available","Not available");
    }
}