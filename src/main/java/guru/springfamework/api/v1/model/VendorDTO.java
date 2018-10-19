package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    @ApiModelProperty(value="The name of the vendor",required = true)
    private String name;
    @ApiModelProperty(value="The resource URL of the vendor",required = false)
    @JsonProperty("vendor_url")
    private String vendor_url;
}
