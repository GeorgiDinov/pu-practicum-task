package com.practicum.pu.georgidinov.shoppinglist.entity;

import com.practicum.pu.georgidinov.shoppinglist.baseentity.BaseNamedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item implements BaseNamedEntity, Serializable {

    private static final long serialVersionUID = 17L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "is_selected")
    private boolean isSelected;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ShoppingListUser user;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Item)) {
            return false;
        }
        Item item = (Item) obj;
        if (!Objects.equals(id, item.id)) {
            return false;
        }
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item = {" +
                "| id = " + this.id +
                "| name = " + this.name +
                "| quantity = " + this.quantity +
                "| isSelected = " + this.isSelected +
                "|}";
    }
}