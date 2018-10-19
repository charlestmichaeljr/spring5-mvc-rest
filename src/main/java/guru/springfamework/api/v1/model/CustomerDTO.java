package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    @ApiModelProperty(value = "The first name of the customer",required = true) // Swagger documentation
    private String firstName;
    private String lastName;
    @JsonProperty("customer_url")
    private String customer_url;
}
