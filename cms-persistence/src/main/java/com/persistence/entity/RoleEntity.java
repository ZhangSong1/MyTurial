package com.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "role_name")
  private String roleName;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "role_endpoint", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "endpoint_id") })
  private List<EndpointEntity> endpoints;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
  private List<UserEntity> users;

  public RoleEntity()
  {
  }

  public int getId()
  {
    return this.id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getRoleName()
  {
    return this.roleName;
  }

  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }

  public List<EndpointEntity> getEndpoints()
  {
    return this.endpoints;
  }

  public void setEndpoints(List<EndpointEntity> endpoints)
  {
    this.endpoints = endpoints;
  }

}
