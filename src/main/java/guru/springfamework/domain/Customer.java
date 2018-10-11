package guru.springfamework.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String customer_url;
}
