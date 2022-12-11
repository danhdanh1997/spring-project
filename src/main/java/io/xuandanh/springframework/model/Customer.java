package io.xuandanh.springframework.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
@Accessors(chain = true)
public class Customer {
    public static final int ACTIVE = 1;
    public static final int UN_ACTIVE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "status")
    private Integer status;

    public Customer(long l, String john, String doe) {
        this.id = l;
        this.firstName = john;
        this.lastName = doe;
    }

    public String getCustomerNames() {
        return firstName + " " + lastName;
    }
}
