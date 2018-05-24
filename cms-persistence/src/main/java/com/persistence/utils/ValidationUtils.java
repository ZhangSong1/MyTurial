package com.persistence.utils;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.EmailValidator;

public class ValidationUtils
{
  public static boolean isEmail(String email)
  {
    return EmailValidator.getInstance().isValid(email);
  }
  
  public static boolean isAlphanumeric(String str){
    String regexp = "^(?:[0-9a-zA-Z])*$";
    return GenericValidator.matchRegexp(str, regexp);
  }
}
