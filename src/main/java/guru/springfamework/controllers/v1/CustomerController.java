package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description="This is my customer controller")
@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    public static final String BASE_URL = "/api/v1/customers/";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Gets all Customers on file",notes = "Lorem Ipsum")
    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) throws Exception {
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(Long.parseLong(id)),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(customerService.updateExistingCustomer(id,customerDTO),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(customerService.patchExistingCustomer(id,customerDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
