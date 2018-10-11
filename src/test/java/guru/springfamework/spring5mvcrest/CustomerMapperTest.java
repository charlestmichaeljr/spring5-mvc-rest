package guru.springfamework.spring5mvcrest;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private final String FIRST_NAME = "Bob";
    private final String LAST_NAME = "Smith";
    private final String CUSTOMER_URL = "/api/v1/customer/1";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testCustomerMapping() {
        // given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setCustomer_url(CUSTOMER_URL);
        // when
        CustomerDTO customerDTO =  customerMapper.customerToCustomerDTO(customer);
        // then
        assertEquals(FIRST_NAME,customerDTO.getFirstName());
        assertEquals(LAST_NAME,customerDTO.getLastName());
        assertEquals(CUSTOMER_URL,customerDTO.getCustomer_url());
    }
}
