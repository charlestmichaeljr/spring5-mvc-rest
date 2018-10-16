package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.controllers.v1.VendorControllerTest;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    private final String NAME = "Rex Traylor";
    private final Long ID = 1L;

    @Mock
    VendorRepository vendorRepository;

    @Mock
    VendorMapper vendorMapper;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository,VendorMapper.INSTANCE);
    }

    @Test
    public void testFindAllVendors() {
        // given
        List<Vendor> vendors = new ArrayList<>();

        Vendor vendor1 = new Vendor();
        vendor1.setName(NAME);
        vendor1.setVendor_url(VendorController.BASE_URL + "2");
        vendor1.setId(ID);
        vendors.add(vendor1);

        vendors.add(new Vendor());
        vendors.add(new Vendor());
        vendors.add(new Vendor());
        // when
        when(vendorRepository.findAll())
                .thenReturn(vendors);
        // then
        List<VendorDTO> returnedVendors = vendorService.findAll();

        assertEquals(returnedVendors.size(),vendors.size());
        assertEquals(NAME,returnedVendors.get(0).getName());
        verify(vendorRepository,times(1)).findAll();
    }

    @Test
    public void testFindVendorById() throws Exception {
        Vendor vendor1 = new Vendor();
        vendor1.setName(NAME);
        vendor1.setVendor_url(VendorController.BASE_URL + "2");
        vendor1.setId(ID);

        Optional<Vendor> optionalVendor = Optional.of(vendor1);

        when(vendorRepository.findById(anyLong())).thenReturn(optionalVendor);

        VendorDTO returnedVendor = vendorService.findById(ID);
        assertEquals(vendor1.getId(),returnedVendor.getId());
        assertEquals(vendor1.getName(),returnedVendor.getName());
        assertEquals(vendor1.getVendor_url(),returnedVendor.getVendor_url());
        verify(vendorRepository,times(1)).findById(anyLong());
    }
}
