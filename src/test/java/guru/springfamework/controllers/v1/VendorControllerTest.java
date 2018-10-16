package guru.springfamework.controllers.v1;


import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.services.VendorService;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    private final Long ID = 1L;
    private final String NAME = "Rex Traylor";
    private final String VENDOR_URL = VendorController.BASE_URL;

    @Mock
    VendorService vendorService;

    MockMvc mockMvc;

    @InjectMocks()
    VendorController vendorController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    public void testGetAllVendors() throws Exception {
        // given
        List<VendorDTO> vendors = new ArrayList<>();
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setId(ID);
        vendor1.setName(NAME);
        vendor1.setVendor_url(VENDOR_URL + ID);
        vendors.add(vendor1);
        vendors.add(new VendorDTO());
        vendors.add(new VendorDTO());

        // when
        when(vendorService.findAll()).thenReturn(vendors);
        mockMvc.perform(get(VENDOR_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors",hasSize(vendors.size())));
        // then
        verify(vendorService,times(1)).findAll();
    }

    @Test
    public void testGetVendorById() throws  Exception {

        // given
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setId(ID);
        vendor1.setName(NAME);
        vendor1.setVendor_url(VENDOR_URL + ID);
        // when
        when(vendorService.findById(ID)).thenReturn(vendor1);
        // then
        mockMvc.perform(get(VENDOR_URL + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME)));

        verify(vendorService,times(1)).findById(anyLong());
    }
}
