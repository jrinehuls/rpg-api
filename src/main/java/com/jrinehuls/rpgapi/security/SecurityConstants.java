package com.jrinehuls.rpgapi.security;

public class SecurityConstants {
    // TODO: Read secret from props. This is insecure for real apps.
    public static final String SECRET = "bQeThWmZq4t7w!z$C&F)J@NcRfUjXn2r5u8x/A?D*G-KaPdSgVkYp3s6v9y$B&E)";
    public static final String AUTHENTICATION_PATH = "/api/user/authenticate";
    public static final int TOKEN_LIFE_MILLIS = 7_200_000; // 2 hours (1000 * 60 * 60 * 2)
}
