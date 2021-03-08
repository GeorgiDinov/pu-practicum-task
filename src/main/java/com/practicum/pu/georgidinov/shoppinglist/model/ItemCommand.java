package com.practicum.pu.georgidinov.shoppinglist.model;

import com.practicum.pu.georgidinov.shoppinglist.baseentity.BaseNamedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCommand implements BaseNamedEntity {

    private Long id;
    private String name;
    private int quantity;

}
