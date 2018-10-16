package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VendorService {
    List<VendorDTO> findAll();
    VendorDTO findById(Long id);
    VendorDTO createVendor(VendorDTO vendorDTO);
    VendorDTO updateVendor(Long id,VendorDTO vendorDTO);
    VendorDTO patchVendor(Long id,VendorDTO vendorDTO);
    void deleteVendor(Long id);
}
