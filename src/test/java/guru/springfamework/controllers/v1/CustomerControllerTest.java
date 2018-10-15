package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.RestResponseEntityExceptionHandler;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    private final Long ID = 1L;
    private final String FIRST_NAME = "Bob";
    private final String LAST_NAME = "Smith";
    private final String CUSTOMER_URL = CustomerController.BASE_URL + "2";

    @Mock
    CustomerService customerService;

    @InjectMocks()
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<CustomerDTO> customers = new ArrayList<>();
        customers.add(new CustomerDTO());
        customers.add(new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomer_url(CUSTOMER_URL);
        customer.setLastName(LAST_NAME);
        customer.setFirstName(FIRST_NAME);
        customer.setId(ID);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get(CustomerController.BASE_URL + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(customerService, times(1)).getCustomerById(anyLong());
    }

    @Test
    public void testCreateNewCustomer() throws Exception {

        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_url(CUSTOMER_URL);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setId(ID);

        // when
        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(customerDTO);

        // then
        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void testUpdateExistingCustomer() throws Exception {
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_url(CUSTOMER_URL);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setId(ID);
        // when
        when(customerService.updateExistingCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTO);
        // then
        mockMvc.perform(put(CustomerController.BASE_URL + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.firstName",equalTo(FIRST_NAME)))
        .andExpect(jsonPath("$.lastName",equalTo(LAST_NAME)));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnedCustomerDTO = new CustomerDTO();
        returnedCustomerDTO.setFirstName(customerDTO.getFirstName());
        returnedCustomerDTO.setLastName(customerDTO.getLastName());
        returnedCustomerDTO.setCustomer_url(CUSTOMER_URL);

        when(customerService.patchExistingCustomer(anyLong(),any(CustomerDTO.class))).thenReturn(returnedCustomerDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName",equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url",equalTo(CUSTOMER_URL)));

    }

    @Test
    public void testDeleteCustomerById() throws Exception {

        // given

        // when
        mockMvc.perform(delete(CustomerController.BASE_URL + "1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // then
        verify(customerService,times(1)).deleteCustomerById(anyLong());
    }
}