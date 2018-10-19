package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "Add, update, delete, and retrieve Fruit Vendor information")
@RestController
@RequestMapping("/api/v1/vendors/")
public class VendorController {

    public static final String BASE_URL =  "/api/v1/vendors/";

    VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Get all Vendors", notes = "Gets all the fruit vendors from the database")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorService.findAll());
    }

    @ApiOperation(value = "Get a vendor by ID", notes = "Gets a single fruit vendor for the ID specified")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @ApiOperation(value = "Create a new vendor", notes = "Input a vendor name, you will get back a link to the saved vendor.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createVendor(vendorDTO);
    }

    @ApiOperation(value = "Update an existing vendor", notes = "Update fruit vendor information")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id,@RequestBody VendorDTO vendorDTO) {
        return vendorService.updateVendor(id,vendorDTO);
    }

    @ApiOperation(value = "Patch a vendor", notes = "Update individual fields on a vendor. You don't have to provide all the vendor properties as input")
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id,@RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id,vendorDTO);
    }

    @ApiOperation(value = "Delete an existing vendor", notes = "Will not fail if vendor does not exist")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }

}
