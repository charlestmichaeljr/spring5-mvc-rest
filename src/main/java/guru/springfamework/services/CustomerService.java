package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id) throws Exception;
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO updateExistingCustomer(Long id,CustomerDTO customerDTO);
    CustomerDTO patchExistingCustomer(Long id,CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
}
