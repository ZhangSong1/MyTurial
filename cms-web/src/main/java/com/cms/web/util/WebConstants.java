package com.cms.web.util;

public interface WebConstants
{
  public final static String DATE_FORMAT = "yyyy年MM月dd日HH:mm:ss";
  public final static String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public final static String NEWS_PATH = "/news";
  public final static String LOGIN_PATH = "/login";
  public final static String LOGIN_FAILED_PATH = "/loginFailed";
  public final static String LOGOUT_PATH = "/logout";
  public final static String MAIN_PATH = "/main";
  public final static String COLLECTIONS_PATH = "/collections";
  public final static String FORGOT_PASSWORD_PATH = "/forgotPwd";
  public final static String SIGN_UP_PATH = "/signUp";

  public final static String URL_REGEX = "^(?:https?://)?[\\w]{1,}(?:\\.?[\\w]{1,})+[\\w-_/?&=#%:]*$";

  public final static String REST_BASE_PATH = "http://localhost:8081/api/v1/";
  
  public final static String REST_NEWS_BASE_PATH = REST_BASE_PATH + "news";
  public final static String REST_NEWS_NAME_PATH = REST_NEWS_BASE_PATH + "/name/";
  
  public final static String REST_FILE_BASE_PATH = REST_BASE_PATH + "file";
  
  public final static String REST_USER_BASE_PATH = REST_BASE_PATH + "users";
  public final static String REST_USER_NAME_PATH = REST_USER_BASE_PATH + "/email/";
  
  public final static String REST_ROLE_BASE_PATH = REST_BASE_PATH + "role";
  
  public final static String REST_FAVORITE_BASE_PATH = REST_BASE_PATH + "favorite";
  
  public final static String REST_FAVORITE_ID_PATH = REST_FAVORITE_BASE_PATH + "/id/";
  
}
