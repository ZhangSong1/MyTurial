package com.persistence.service.orm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistence.dao.ShortcutDao;
import com.persistence.entity.ShortcutEntity;

@Service
public class ShortcutServiceImpl implements ShortcutService
{
  private ShortcutDao shortcutDao;

  @Autowired
  public ShortcutServiceImpl(ShortcutDao shortcutDao)
  {
    this.shortcutDao = shortcutDao;
  }

  @Override
  public int save(ShortcutEntity shortcut)
  {
    return shortcutDao.save(shortcut).getId();
  }

  @Override
  public List<ShortcutEntity> queryAll()
  {
    return shortcutDao.findAll();
  
  }

  @Override
  public void delete(int id)
  {
    shortcutDao.delete(id);
  }
}
