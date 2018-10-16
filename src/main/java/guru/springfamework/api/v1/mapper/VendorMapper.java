package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(source = "id",target = "id")
    VendorDTO vendorToVendorDTO(Vendor vendor);

    @Mapping(source = "id",target = "id")
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
