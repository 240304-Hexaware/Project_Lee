package com.revature.project.parser.payload.request;

import jakarta.validation.constraints.NotNull;

public record TaskRequest(
    @NotNull String userId,
    @NotNull String rawFileId,
    @NotNull String specId) {

}
