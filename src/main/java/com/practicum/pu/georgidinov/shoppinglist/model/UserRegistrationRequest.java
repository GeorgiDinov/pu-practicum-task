package com.practicum.pu.georgidinov.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserRegistrationRequest {

    private String firstName;
    private String lastName;

    @JsonProperty("user_credentials")
    private UserCredentials userCredentials;

}