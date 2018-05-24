package com.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistence.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer>
{
  public UserEntity findByEmail(String email);
}
