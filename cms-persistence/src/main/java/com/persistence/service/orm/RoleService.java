package com.persistence.service.orm;

import java.util.List;
import com.persistence.entity.RoleEntity;

public interface RoleService
{
  public List<RoleEntity> findAll();
}
