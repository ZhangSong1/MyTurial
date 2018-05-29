package org.cms.auth.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class TokenWapper implements RowMapper<Token>, EntityMapper<Token>
{

  @Override
  public Token mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    Token token = new Token();
    token.setUsername(rs.getString("username"));
    token.setLastUsed(rs.getTimestamp("lastUsed"));
    token.setSeries(rs.getString("series"));
    token.setToken(rs.getString("token"));
    return token;
  }

  @Override
  public Map<String, Object> entityMapper(Token token)
  {
    Map<String, Object> mapper = new HashMap<String, Object>();
    mapper.put("username", token.getUsername());
    mapper.put("series", token.getSeries());
    mapper.put("token", token.getToken());
    mapper.put("lastUsed", token.getLastUsed());
    return mapper;
  }

}
