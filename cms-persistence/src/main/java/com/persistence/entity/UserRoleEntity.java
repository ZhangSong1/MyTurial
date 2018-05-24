package com.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name = "user_role")
public class UserRoleEntity implements Serializable
{
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private UserRolePK id;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinColumn(insertable = false, updatable = false)
  private List<RoleEntity> roles;

  public UserRoleEntity()
  {
  }

  public UserRolePK getId()
  {
    return this.id;
  }

  public void setId(UserRolePK id)
  {
    this.id = id;
  }

  public List<RoleEntity> getRoles()
  {
    return roles;
  }

  public void setRoles(List<RoleEntity> roles)
  {
    this.roles = roles;
  }

}
