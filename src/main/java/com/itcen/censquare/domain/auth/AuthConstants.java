package com.itcen.censquare.domain.auth;

public class AuthConstants {

  public static final String COOKIE_ACCESS_TOKEN = "itcen_access_token";
  public static final String COOKIE_REFRESH_TOKEN = "itcen_refresh_token";
  public static final String REDIS_REFRESH_TOKEN_PREFIX = "RT:";
  public static final long ACCESS_TOKEN_EXPIRATION_MINUTES = 30;
  public static final long REFRESH_TOKEN_EXPIRATION_DAYS = 7;

}
