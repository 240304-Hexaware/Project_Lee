package com.revature.project.parser.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotNull @NotBlank(message = "username must not be blank") @Size(min = 3, message = "username must be longer than 3 characters") String username,
        @NotNull @NotBlank(message = "password must not be blank") @Size(min = 3, message = "password must be longer than 3 characters") String password) {
}
