package guru.springfamework.bootstrap;

import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();

        loadCustomers();

        loadVendors();
    }

    private void loadCustomers() {
        Customer bob = new Customer();
        bob.setFirstName("Bob");
        bob.setLastName("Smith");
        bob.setCustomer_url(CustomerController.BASE_URL + "1");

        Customer carol = new Customer();
        carol.setFirstName("Carol");
        carol.setLastName("Smith");
        carol.setCustomer_url(CustomerController.BASE_URL + "2");

        Customer ted = new Customer();
        ted.setFirstName("Ted");
        ted.setLastName("Jones");
        ted.setCustomer_url(CustomerController.BASE_URL + "3");

        Customer alice = new Customer();
        alice.setFirstName("Alice");
        alice.setLastName("Jones");
        alice.setCustomer_url(CustomerController.BASE_URL + "4");

        List<Customer> customers = Arrays.asList(bob,carol,ted,alice);

        customerRepository.saveAll(customers);
        System.out.println("Customers Loaded = " + customerRepository.count());
    }

    private void loadCategories() {
        List<Category> categories = new ArrayList<>();

        Category fruits = new Category();
        fruits.setName("Fruits");
        categories.add(fruits);

        Category dried = new Category();
        fruits.setName("Dried");
        categories.add(dried);

        Category fresh = new Category();
        dried.setName("Fresh");
        categories.add(fresh);

        Category exotic = new Category();
        exotic.setName("Exotic");
        categories.add(exotic);

        Category nuts = new Category();
        nuts.setName("Nuts");
        categories.add(nuts);

        categoryRepository.saveAll(categories);

        System.out.println("Data Loaded = " + categoryRepository.count() + " categories.");
    }

    private void loadVendors() {

        List<Vendor> vendors = new ArrayList<>();

        Vendor vendor1 = new Vendor();
        vendor1.setName("Michael Brothers");
        vendor1.setVendor_url(VendorController.BASE_URL + "1");
        vendors.add(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Tourtellot");
        vendor2.setVendor_url(VendorController.BASE_URL + "2");
        vendors.add(vendor2);

        Vendor vendor3 = new Vendor();
        vendor3.setName("Farm Fresh");
        vendor3.setVendor_url(VendorController.BASE_URL + "3");
        vendors.add(vendor3);

        vendorRepository.saveAll(vendors);
    }
}
