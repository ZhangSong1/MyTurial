package org.cms.auth.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.cms.auth.domain.Token;
import org.cms.auth.exception.InvalidDataException;
import org.springframework.util.Assert;

public class TokenUtils
{
  public static Token generateToken(String username)
  {
    return generateToken(username, null);
  }

  public static Token generateToken(String username, String series)
  {
    Token token = new Token();
    String tokenStr = System.currentTimeMillis() + username;
    String newSeries = StringUtils.isBlank(series) ? UUID.randomUUID().toString() : series;
    MessageDigest md;
    try
    {
      md = MessageDigest.getInstance("md5");
      byte[] md5 = md.digest(tokenStr.getBytes());
      StringBuffer str = new StringBuffer();
      str.append(username).append(":").append(newSeries).append(":").append(new String(md5));
      token.setToken(new String(Base64.getEncoder().encode(str.toString().getBytes())));
      token.setUsername(username);
      token.setSeries(newSeries);
      Timestamp expiredTime = new Timestamp(DateUtils.addDays(new Date(), 3).getTime());
      token.setLastUsed(expiredTime);
    }
    catch(NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    return token;
  }

  public static Token decodeToken(String token)
  {
    Assert.notNull(token, "Token cannot be empty.");
    String decodeToken = new String(Base64.getDecoder().decode(token));
    String[] data = decodeToken.split(":");
    if(data.length < 3)
      throw new InvalidDataException("Invalid token.");
    Token result = new Token();
    result.setUsername(data[0]);
    result.setSeries(data[1]);
    result.setToken(data[2]);
    return result;
  }
}
