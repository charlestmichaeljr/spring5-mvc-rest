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
        vendor.setVendor_url(VENDOR_URL);

        VendorDTO vendorDTO =  mapper.vendorToVendorDTO(vendor);

        assertEquals(ID,vendorDTO.getId());
        assertEquals(NAME,vendorDTO.getName());
        assertEquals(VENDOR_URL,vendorDTO.getVendor_url());
    }

    @Test
    public void testVendorDTOToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(NAME);
        vendorDTO.setVendor_url(VENDOR_URL);

        Vendor vendor =  mapper.vendorDTOToVendor(vendorDTO);

        assertEquals(ID,vendor.getId());
        assertEquals(NAME,vendor.getName());
        assertEquals(VENDOR_URL,vendor.getVendor_url());
    }
}
