package org.cms.auth.dao;

import javax.annotation.Resource;

import org.cms.auth.entity.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class UserDao
{
  @Resource
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public User getUserByName(String username)
  {
    SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", username);
    String sql = "select id,email,password from users where email = :email";
    return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, User.class);
  }
}
