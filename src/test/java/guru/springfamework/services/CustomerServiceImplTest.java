package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    private final Long ID = 1L;
    private final String FIRST_NAME = "Bob";
    private final String LAST_NAME = "Smith";
    private final String CUSTOMER_URL = CustomerController.BASE_URL +  "2";

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository,CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setFirstName("Zazu");
        customer1.setLastName("Petals");
        customer1.setCustomer_url(CustomerController.BASE_URL +  "3");
        customers.add(customer1);

        customers.add(new Customer());
        customers.add(new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> returnedCustomers = customerService.getAllCustomers();

        assertEquals(3,returnedCustomers.size());
        assertEquals("Zazu",returnedCustomers.get(0).getFirstName());
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setCustomer_url(CUSTOMER_URL);

        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);

        CustomerDTO returnedDTO = customerService.getCustomerById(ID);

        assertEquals(ID,returnedDTO.getId());
        assertEquals(FIRST_NAME,returnedDTO.getFirstName());
        assertEquals(LAST_NAME,returnedDTO.getLastName());
        assertEquals(CUSTOMER_URL,returnedDTO.getCustomer_url());
    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        // given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setCustomer_url(CUSTOMER_URL);

        // when
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerDTO returnedCustomerDTO = customerService.createNewCustomer(new CustomerDTO());
        // then
        assertEquals(ID,returnedCustomerDTO.getId());
        assertEquals(FIRST_NAME,returnedCustomerDTO.getFirstName());
        assertEquals(LAST_NAME,returnedCustomerDTO.getLastName());
        assertEquals(CUSTOMER_URL,returnedCustomerDTO.getCustomer_url());
        verify(customerRepository,times(1)).save(any(Customer.class));
    }

    @Test
    public void testUpdateExistingCustomer() throws Exception {

        // given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setCustomer_url(CUSTOMER_URL);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        // when
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerDTO returnedCustomer = customerService.updateExistingCustomer(ID,customerDTO);

        // then
        assertEquals(FIRST_NAME,returnedCustomer.getFirstName());
        assertEquals(LAST_NAME,returnedCustomer.getLastName());
        verify(customerRepository,times(1)).save(any(Customer.class));
    }

    @Test
    public void testDeleteById() throws Exception {
        customerService.deleteCustomerById(ID);

        verify(customerRepository,times(1)).deleteById(anyLong());
    }
}