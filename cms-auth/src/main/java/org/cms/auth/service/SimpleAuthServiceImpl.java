package org.cms.auth.service;

import java.util.Date;

import javax.annotation.Resource;

import org.cms.auth.dao.TokenDao;
import org.cms.auth.dao.UserDao;
import org.cms.auth.entity.Token;
import org.cms.auth.entity.User;
import org.cms.auth.exception.InvalidDataException;
import org.cms.auth.exception.UserNotFoundException;
import org.springframework.util.Assert;

import cms.utils.PwdEncode;

public class SimpleAuthServiceImpl implements AuthService
{
  @Resource
  private UserDao userDao;

  @Resource
  private TokenDao tokenDao;

  @Override
  public String auth(String username, String password)
  {
    Assert.notNull(username, "Please input username.");
    Assert.notNull(password, "Please input password.");
    User user = userDao.getUserByName(username);
    if(user == null)
      throw new UserNotFoundException();
    if(PwdEncode.matches(user.getPassword(), password))
    {
      Token token = TokenUtils.generateToken(username);
      tokenDao.save(token);
      return token.getToken();
    }
    else
    {
      throw new InvalidDataException();
    }
  }

  @Override
  public String auth(String tokenStr)
  {
    Assert.notNull(tokenStr, "Please input token.");
    Token tokenDetail = TokenUtils.decodeToken(tokenStr);
    Token token = tokenDao.getToken(tokenDetail.getUsername(), tokenDetail.getSeries());
    if(token == null || !tokenDetail.getToken().equals(token.getToken()) || token.getLastUsed().before(new Date()))
      throw new InvalidDataException();
    return refreshToken(token).getToken();
  }

  @Override
  public Token refreshToken(Token oldToken)
  {
    Token newToken = TokenUtils.generateToken(oldToken.getUsername(), oldToken.getSeries());
    tokenDao.update(newToken);
    return newToken;

  }

  @Override
  public void logout(String tokenStr)
  {
    Assert.notNull(tokenStr, "Please input token.");
    Token tokenDetail = TokenUtils.decodeToken(tokenStr);
    tokenDao.removeToken(tokenDetail.getUsername());
  }

}
