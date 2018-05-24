package com.cms.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import com.cms.web.model.Menu;
import com.cms.web.model.Role;
import com.cms.web.service.UserService;

/**
 * @description 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色访问
 * @author aokunsang
 * @date 2012-8-15
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{

  private static final Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

  private UserService userService;

  /* 保存资源和权限的对应关系 key-资源url value-权限 */
  private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

  private AntPathMatcher urlMatcher = new AntPathMatcher();

  MySecurityMetadataSource(UserService userService)
  {
    this.userService = userService;
    loadAllRole();
  }

  private void loadAllRole()
  {
    resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
    List<Role> roles = userService.getAllRoles();
    roles.stream().forEach(role -> {
      Set<Menu> resources = role.getMenus();
      resources.stream().forEach(menu -> {
        Collection<ConfigAttribute> configAttributes = null;
        ConfigAttribute configAttribute = new SecurityConfig(menu.getMenuName());
        if(resourceMap.containsKey(menu.getEndpoint()))
        {
          configAttributes = resourceMap.get(menu.getEndpoint());
          configAttributes.add(configAttribute);
        }
        else
        {
          configAttributes = new ArrayList<ConfigAttribute>();
          configAttributes.add(configAttribute);
          resourceMap.put(menu.getEndpoint(), configAttributes);
        }
      });
    });
  }

  @Override
  public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException
  {
    // 获取请求的url地址
    String url = ((FilterInvocation)obj).getRequestUrl();
    logger.info("MySecurityMetadataSource:getAttributes()---------------请求地址为：" + url);
    Iterator<String> it = resourceMap.keySet().iterator();
    while(it.hasNext())
    {
      String _url = it.next();
      if(_url.indexOf("?") != -1)
      {
        _url = _url.substring(0, _url.indexOf("?"));
      }
      if(urlMatcher.match(_url, url))
      {
        logger.info("MySecurityMetadataSource:getAttributes()---------------需要的权限是：" + resourceMap.get(_url));
        return resourceMap.get(_url);
      }

    }
    Collection<ConfigAttribute> nouse = new ArrayList<ConfigAttribute>();
    nouse.add(new SecurityConfig("无相应权限"));
    return nouse;
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes()
  {
    return null;
  }

  @Override
  public boolean supports(Class<?> clazz)
  {
    // TODO Auto-generated method stub
    return true;
  }

  public UserService getUserService()
  {
    return userService;
  }

  public void setUserService(UserService userService)
  {
    this.userService = userService;
  }

}
