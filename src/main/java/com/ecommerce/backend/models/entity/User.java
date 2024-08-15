package com.ecommerce.backend.models.entity;

import com.ecommerce.backend.models.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    private long id;
    private String name;
    private String email;
    private String number;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRoles roles;
    private LocalDate createdOn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Address> addressList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Set<Orders> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Payment> paymentList;

    @ManyToMany(mappedBy = "userSet")
    @JsonIgnoreProperties("userSet")
    private Set<Review> reviewSet;

    @ManyToMany
    @JoinTable(
            name = "seller_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnoreProperties("cartItem")
    private Set<Product> products;
}
