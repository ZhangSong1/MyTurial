package com.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistence.entity.RoleEntity;

public interface RoleDao extends JpaRepository<RoleEntity, Integer>
{

}
