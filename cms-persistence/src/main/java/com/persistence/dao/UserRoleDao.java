package com.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistence.entity.UserRoleEntity;

public interface UserRoleDao extends JpaRepository<UserRoleEntity, Integer>
{

}
