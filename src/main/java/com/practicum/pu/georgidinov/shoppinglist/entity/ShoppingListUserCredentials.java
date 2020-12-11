package com.practicum.pu.georgidinov.shoppinglist.entity;

import com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_credentials")
public class ShoppingListUserCredentials {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_credentials_id")
    private Long id;

    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private ShoppingListUser user;

    @Enumerated(value = EnumType.STRING)
    private ShoppingListUserRole userRole;


    //== public methods ==
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingListUserCredentials)) {
            return false;
        }

        ShoppingListUserCredentials that = (ShoppingListUserCredentials) o;

        if (!id.equals(that.id)) {
            return false;
        }
        if (!Objects.equals(username, that.username)) {
            return false;
        }
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingListUserCredentials{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}