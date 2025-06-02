package matalvesdev.CustomerConnect.controller;


import matalvesdev.CustomerConnect.controller.dto.ApiResponse;
import matalvesdev.CustomerConnect.controller.dto.CreateCustomerDto;
import matalvesdev.CustomerConnect.controller.dto.PaginationResponse;
import matalvesdev.CustomerConnect.controller.dto.UpdateCustomerDto;
import matalvesdev.CustomerConnect.entity.CustomerEntity;
import matalvesdev.CustomerConnect.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping (path = "/customers")
public class CustomerController {


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private final CustomerService customerService;





    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerDto dto) {

       var customer = customerService.createCustomer(dto);
        return ResponseEntity.created(URI.create("/customers/" + customer.getCustomerId()))
                .build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerEntity>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                               @RequestParam(name = "orderBy", defaultValue = "desc") String orderBy,
                                                               @RequestParam(name = "cpf", required = false) String cpf,
                                                               @RequestParam(name = "email", required = false) String email){

       var pageResp = customerService.findAll(page, pageSize, orderBy, cpf, email);
        return ResponseEntity.ok(new ApiResponse<>(
                pageResp.getContent(),
                new PaginationResponse(pageResp.getNumber(),
                                       pageResp.getSize(),
                                       pageResp.getTotalElements(),
                                       pageResp.getTotalPages()
        ))
        );
    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> findById(@PathVariable("customerId") Long customrId) {

        var customer = customerService.findById(customrId);

        return customer.isPresent() ?
                ResponseEntity.ok(customer.get()) :
                ResponseEntity.notFound().build();

    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> updateById(@PathVariable("customerId") Long customrId,
                                                     @RequestBody UpdateCustomerDto dto) {

        var customer = customerService.updateById(customrId, dto);

        return customer.isPresent() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> deleteById(@PathVariable("customerId") Long customrId) {

        var deleted = customerService.deleteById(customrId);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();

    }

}
