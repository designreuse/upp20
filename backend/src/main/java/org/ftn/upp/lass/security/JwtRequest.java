package org.ftn.upp.lass.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtRequest implements Serializable {

    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
}
