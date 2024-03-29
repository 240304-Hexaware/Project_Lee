package com.revature.project.parser.payload.request;

import jakarta.validation.constraints.NotNull;

public record TaskRequest(
                @NotNull(message = "rawFileId must be present") String rawFileId,
                @NotNull(message = "specId must be present") String specId) {

}
