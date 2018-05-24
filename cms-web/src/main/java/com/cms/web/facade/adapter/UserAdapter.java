package com.cms.web.facade.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cms.web.dto.RoleDto;
import com.cms.web.dto.UserDto;
import com.cms.web.model.Menu;
import com.cms.web.model.Role;
import com.cms.web.model.User;

public class UserAdapter
{
  public static User convertToUser(UserDto userDto)
  {
    if(userDto == null)
      return null;
    User user = new User();
    user.setId(userDto.getId());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    Set<Role> roles= new HashSet<Role>();
    roles.addAll(convertToRole(userDto.getRoles()));
    user.setRoles(roles);
    return user;
  }

  public static List<Role> convertToRole(List<RoleDto> roles)
  {
    List<Role> list = new ArrayList<Role>(roles.size());
    roles.parallelStream().forEach(role -> {
      list.add(convertToRole(role));
    });
    return list;
  }

  public static Role convertToRole(RoleDto role)
  {
    Set<Menu> menus = new HashSet<Menu>();
    role.getEndpoints().parallelStream().forEach(endpoint -> {
      menus.add(new Menu(endpoint.getId(), endpoint.getName(), endpoint.getEndpoint()));
    });
    return new Role(role.getId(), role.getRoleName(), menus);
  }
}
