package com.revature.project.parser.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

  private PasswordEncoderUtil() {

  }

  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public static String encodePassword(String rawPassword) {
    return encoder.encode(rawPassword);
  }

  public static boolean matches(String rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }
}
