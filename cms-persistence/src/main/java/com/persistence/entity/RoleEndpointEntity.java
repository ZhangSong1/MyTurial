package com.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the role_endpoint database table.
 * 
 */
@Entity
@Table(name = "role_endpoint")
public class RoleEndpointEntity implements Serializable
{
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private RoleEndpointPK id;

  // bi-directional many-to-one association to Role
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(insertable = false, updatable = false)
  private RoleEntity role;

  public RoleEndpointEntity()
  {
  }

  public RoleEndpointPK getId()
  {
    return this.id;
  }

  public void setId(RoleEndpointPK id)
  {
    this.id = id;
  }

  public RoleEntity getRole()
  {
    return this.role;
  }

  public void setRole(RoleEntity role)
  {
    this.role = role;
  }

}
