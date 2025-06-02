package matalvesdev.CustomerConnect.service;

import matalvesdev.CustomerConnect.controller.dto.CreateCustomerDto;
import matalvesdev.CustomerConnect.controller.dto.UpdateCustomerDto;
import matalvesdev.CustomerConnect.entity.CustomerEntity;
import matalvesdev.CustomerConnect.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity createCustomer(CreateCustomerDto dto) {

        var entity = new CustomerEntity();

        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setCpf(dto.cpf());
        entity.setPhoneNumber(dto.phoneNumber());

        return customerRepository.save(entity);
    }

    public Page<CustomerEntity> findAll(Integer page,
                                        Integer pageSize,
                                        String orderBy,
                                        String cpf,
                                        String email) {


       var pageRequest = getPageRequest(page, pageSize, orderBy);

        return findWithFilter(cpf, email, pageRequest);


    }

    private Page<CustomerEntity> findWithFilter(String cpf, String email, PageRequest pageRequest) {
        if (hasText(cpf) && hasText(email)) {
            return customerRepository.findByCpfAndEmail(cpf, email, pageRequest);
        }

        if (hasText(cpf)) {
            return customerRepository.findByCpf(cpf, pageRequest);
        }


        if (hasText(cpf)) {
            return customerRepository.findByEmail(email, pageRequest);
        }

        return customerRepository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(Integer page, Integer pageSize, String orderBy) {
        var direction = Sort.Direction.DESC;
        if (orderBy.equalsIgnoreCase("asc")) {
            direction = Sort.Direction.ASC;
        }
       return PageRequest.of(page, pageSize, direction, "createdAt");
    }

    public Optional<CustomerEntity> findById(Long customrId) {
            return customerRepository.findById(customrId);
    }

    public Optional<CustomerEntity> updateById(Long customrId, UpdateCustomerDto dto) {

        var customer = customerRepository.findById(customrId);

        if (customer.isPresent()) {

            updatedFields(dto, customer);

            customerRepository.save(customer.get());
        }

        return customer;
    }

    private void updatedFields(UpdateCustomerDto dto, Optional<CustomerEntity> customer) {
        if (hasText(dto.name())) {
            customer.get().setName(dto.name());
        }

        if (hasText(dto.email())) {
            customer.get().setEmail(dto.email());
        }

        if (hasText(dto.phoneNumber())) {
            customer.get().setPhoneNumber(dto.phoneNumber());
        }
    }

    public boolean deleteById(Long customrId) {

       var exists = customerRepository.existsById(customrId);

        if (exists) {
            customerRepository.deleteById(customrId);
            return true;
        }
        return exists;
    }

    }

