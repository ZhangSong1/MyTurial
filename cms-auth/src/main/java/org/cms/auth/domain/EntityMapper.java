package org.cms.auth.domain;

import java.util.Map;

public interface EntityMapper<T>
{
  public Map<String, Object> entityMapper(T t);
}
