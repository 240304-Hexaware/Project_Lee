package com.revature.project.parser.payload.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotNull String username,
    @NotNull String password) {

}
