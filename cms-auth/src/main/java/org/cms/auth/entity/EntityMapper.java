package org.cms.auth.entity;

import java.util.Map;

public interface EntityMapper<T>
{
  public Map<String, Object> entityMapper(T t);
}
