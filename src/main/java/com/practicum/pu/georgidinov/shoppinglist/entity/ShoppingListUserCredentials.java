package com.practicum.pu.georgidinov.shoppinglist.entity;

import com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole.USER;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ShoppingListUserCredentials {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ShoppingListUser user;

    @Enumerated(value = EnumType.STRING)
    private ShoppingListUserRole userRole = USER;

}