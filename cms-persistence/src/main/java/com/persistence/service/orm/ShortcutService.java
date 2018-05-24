package com.persistence.service.orm;

import java.util.List;

import com.persistence.entity.ShortcutEntity;

public interface ShortcutService
{
  public int save(ShortcutEntity shortcut);
  public List<ShortcutEntity> queryAll();
  public void delete(int id);
}
