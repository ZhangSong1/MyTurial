package com.persistence.service.orm;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistence.dao.RoleDao;
import com.persistence.entity.RoleEntity;

@Service
public class RoleServiceImpl implements RoleService
{

  private RoleDao roleDao;
  
  
  
  @Autowired
  public RoleServiceImpl(RoleDao roleDao)
  {
    this.roleDao = roleDao;
  }



  @Override
  public List<RoleEntity> findAll()
  {
    // TODO Auto-generated method stub
    return roleDao.findAll();
  }

}
