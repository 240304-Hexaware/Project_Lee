package com.revature.project.parser.payload.response;

public record LoginResponse(String userId, String username, boolean isAdmin, String token) {

}
