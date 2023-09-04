package com.josdem.auth.util;

public class CredentialsEncoder {

  public static String encode(String username, String password) {
    return username + ":" + password;
  }
}
