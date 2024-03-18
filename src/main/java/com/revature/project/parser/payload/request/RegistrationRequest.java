package com.revature.project.parser.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequest(
                @NotNull String username,
                @NotNull @Min(3) String password,
                @NotNull @Min(3) String confirmPassword) {

}
