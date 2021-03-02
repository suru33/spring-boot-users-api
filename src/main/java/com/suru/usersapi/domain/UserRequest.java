package com.suru.usersapi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 136542589652L;

    @NotBlank(message = "Invalid first name")
    private String firstName;

    @NotBlank(message = "Invalid last name")
    private String lastName;

    @NotBlank(message = "Invalid phone name")
    private String phone;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Invalid address line 1")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "Invalid city")
    private String city;

    @NotBlank(message = "Invalid state")
    private String state;

    @NotBlank(message = "Invalid zip")
    private String zip;

    @NotBlank(message = "Invalid country code")
    @Size(min = 2, max = 2, message = "Invalid country code")
    private String countryCode;
}
