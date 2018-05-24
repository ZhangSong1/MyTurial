package com.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistence.entity.RoleEndpointEntity;

public interface RoleEndpointDao extends JpaRepository<RoleEndpointEntity, Integer>
{

}
