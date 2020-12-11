package com.practicum.pu.georgidinov.shoppinglist.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserCommand {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

}