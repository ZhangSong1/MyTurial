package com.persistence.service.orm;

import java.util.List;

import com.persistence.entity.UserEntity;

public interface UserService
{
  public UserEntity getUserByEmail(String email);
  public int save(UserEntity userEntity);
  public void resetPwd(UserEntity userEntity);
  public List<UserEntity> find(UserEntity userEntity);
  public boolean exist(UserEntity userEntity);
  public boolean exist(String email);
}
