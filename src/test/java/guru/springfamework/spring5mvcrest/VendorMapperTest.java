package guru.springfamework.spring5mvcrest;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    private final VendorMapper mapper = VendorMapper.INSTANCE;

    private final Long ID = 1L;
    private final String NAME = "Some Vendor";
    private final String VENDOR_URL = "/api/v1/vendors";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testVendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO =  mapper.vendorToVendorDTO(vendor);

        assertEquals(NAME,vendorDTO.getName());

    }

    @Test
    public void testVendorDTOToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendor_url(VENDOR_URL);

        Vendor vendor =  mapper.vendorDTOToVendor(vendorDTO);

        assertEquals(NAME,vendor.getName());
    }
}
