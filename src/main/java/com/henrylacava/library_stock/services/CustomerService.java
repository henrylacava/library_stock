package com.henrylacava.library_stock.services;

import com.henrylacava.library_stock.dto.CustomerDto;
import com.henrylacava.library_stock.dto.CustomerResponseDto;
import com.henrylacava.library_stock.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerResponseDto getAllCustomersPaginated(int page, int size) {
        Page<CustomerDto> customerPage = customerRepository.findCustomers(PageRequest.of(page, size));

        List<CustomerDto> customers = customerPage.getContent();

        Long totalElements = customerPage.getTotalElements();
        Integer totalPages = customerPage.getTotalPages();
        Boolean isFirst = customerPage.isFirst();
        Boolean isLast = customerPage.isLast();

        return new CustomerResponseDto(
                customers, totalElements, totalPages, isFirst, isLast, page
        );
    }
}
