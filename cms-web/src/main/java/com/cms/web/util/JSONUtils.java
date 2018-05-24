package com.cms.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cms.web.exception.CmsServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils
{

  private final static ObjectMapper objectMapper = new ObjectMapper();

  private JSONUtils()
  {

  }

  /**
   * javaBean,list,array convert to json string
   */
  public static String obj2json(Object obj)
  {
    try
    {
      return objectMapper.writeValueAsString(obj);
    }
    catch(JsonProcessingException e)
    {
      throw new CmsServiceException(e.getMessage());
    }
  }

  /**
   * json string convert to javaBean
   */
  public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception
  {
    return objectMapper.readValue(jsonStr, clazz);
  }

  /**
   * json string convert to map
   */
  @SuppressWarnings("unchecked")
  public static <T> Map<String, Object> json2map(String jsonStr)
  {
    try
    {
      return objectMapper.readValue(jsonStr, Map.class);
    }
    catch(Exception e)
    {
      throw new CmsServiceException("Convert json2map failed," + jsonStr);
    }

  }

  /**
   * json string convert to map with javaBean
   */
  public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception
  {
    Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>()
    {
    });
    Map<String, T> result = new HashMap<String, T>();
    for(Entry<String, Map<String, Object>> entry : map.entrySet())
    {
      result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
    }
    return result;
  }

  /**
   * json array string convert to list with javaBean
   */
  public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception
  {
    List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>()
    {
    });
    List<T> result = new ArrayList<T>();
    for(Map<String, Object> map : list)
    {
      result.add(map2pojo(map, clazz));
    }
    return result;
  }

  /**
   * map convert to javaBean
   */
  @SuppressWarnings("rawtypes")
  public static <T> T map2pojo(Map map, Class<T> clazz)
  {
    return objectMapper.convertValue(map, clazz);
  }
}