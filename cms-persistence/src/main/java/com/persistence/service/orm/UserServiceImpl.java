package com.persistence.service.orm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.persistence.dao.UserDao;
import com.persistence.entity.UserEntity;
import com.persistence.rest.exception.CmsException;
import com.persistence.rest.exception.ErrorMessage;

@Service
@Transactional
public class UserServiceImpl implements UserService
{

  private UserDao userDao;

  private ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "password");

  @Autowired
  public UserServiceImpl(UserDao userDao)
  {
    this.userDao = userDao;
  }

  @Override
  public UserEntity getUserByEmail(String email)
  {
    UserEntity userEntity = new UserEntity(email);
    if(exist(userEntity))
    {

      return find(userEntity).get(0);
    }
    else
    {
      throw new CmsException(ErrorMessage.NOT_FOUND, email);
    }
  }

  @Override
  public int save(UserEntity userEntity)
  {
    UserEntity result = userDao.save(userEntity);
    if(result == null)
    {
      throw new CmsException(ErrorMessage.INTERNAL_SERVER_ERROR);
    }
    else
    {
      return result.getId();
    }
  }

  @Override
  public void resetPwd(UserEntity userEntity)
  {
    if(getUserByEmail(userEntity.getEmail()) == null)
    {
      throw new CmsException(ErrorMessage.NOT_FOUND, userEntity.getEmail());
    }
    else
    {
      save(userEntity);
    }

  }

  @Override
  public List<UserEntity> find(UserEntity userEntity)
  {
    return userDao.findAll(Example.of(userEntity, matcher));
  }

  @Override
  public boolean exist(UserEntity userEntity)
  {
    return userDao.exists(Example.of(userEntity, matcher));
  }

  @Override
  public boolean exist(String email)
  {
    return exist(new UserEntity(email));
  }

}
