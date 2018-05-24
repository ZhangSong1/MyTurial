package com.cms.web.util;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PwdEncode implements PasswordEncoder
{

  @Override
  public String encode(CharSequence rawPassword)
  {
    return cms.utils.PwdEncode.encode(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword)
  {
    return cms.utils.PwdEncode.encode(rawPassword).equals(encodedPassword);
  }

}
