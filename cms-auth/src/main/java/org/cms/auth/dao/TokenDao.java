package org.cms.auth.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.cms.auth.entity.Token;
import org.cms.auth.entity.TokenWapper;
import org.cms.auth.exception.SQLFailedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class TokenDao
{
  @Resource
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  @Resource
  private JdbcTemplate classicJdbcTemplate;

  public void save(Token token)
  {
    SimpleJdbcInsert insert = new SimpleJdbcInsert(classicJdbcTemplate).withTableName("persistent_logins");
    Map<String, Object> parameters = new TokenWapper().entityMapper(token);
    if(insert.execute(parameters) == 0)
    {
      throw new SQLFailedException();
    }
  }

  public void update(Token token)
  {
    String sql = "update  persistent_logins set token= :token and last_used =:last_used where username =:username and series=:series";
    SqlParameterSource namedParameters =
        new MapSqlParameterSource().addValue("username", token.getUsername()).addValue("series", token.getSeries())
            .addValue("token", token.getToken()).addValue("last_used", token.getLastUsed());
    if(namedParameterJdbcTemplate.update(sql, namedParameters) == 0)
    {
      throw new SQLFailedException(sql);
    }
  }

  public Token getToken(String username, String series)
  {

    String sql = "select username,series,token,last_used from persistent_logins where username =:username and series=:series";
    SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("username", username).addValue("series", series);
    return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TokenWapper());
  }

  public void removeToken(String username)
  {
    String sql = "delete from persistent_logins where username =:username";
    SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("username", username);
    namedParameterJdbcTemplate.execute(sql, namedParameters, null);
  }

}
