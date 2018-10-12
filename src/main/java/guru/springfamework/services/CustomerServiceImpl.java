package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws Exception {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(Exception::new);

    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
       Customer savedCustomer = customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
       return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO updateExistingCustomer(Long id,CustomerDTO customerDTO) {
        Customer updatedCustomer = customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
        return customerMapper.customerToCustomerDTO(updatedCustomer);
    }
}
