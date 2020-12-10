package com.practicum.pu.georgidinov.shoppinglist.command;

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
    private String itemName;
    private int quantity;
}
