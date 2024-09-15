package com.henrylacava.library_stock.repositories;

import com.henrylacava.library_stock.dto.CustomerDto;
import com.henrylacava.library_stock.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT new com.henrylacava.library_stock.dto.CustomerDto(c.firstName, c.lastName, c.email) FROM Customer c")
    Page<CustomerDto> findCustomers(Pageable pageable);
}
