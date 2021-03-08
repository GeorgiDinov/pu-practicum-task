package com.practicum.pu.georgidinov.shoppinglist.model;

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
public class SavedItemCommand {

    private Long id;
    private String name;
    private int quantity;
    private boolean isSelected;
}
