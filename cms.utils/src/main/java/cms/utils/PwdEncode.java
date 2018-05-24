package cms.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class PwdEncode
{

  public static String encode(CharSequence rawPassword)
  {
    return DigestUtils.md5Hex(rawPassword.toString());
  }

  public static boolean matches(CharSequence rawPassword, String encodedPassword)
  {
    return encode(rawPassword).equals(encodedPassword);
  }

}
