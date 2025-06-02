package matalvesdev.CustomerConnect.controller.dto;

public record CreateCustomerDto(String name,
                                String cpf,
                                String email,
                                String phoneNumber) {
}
