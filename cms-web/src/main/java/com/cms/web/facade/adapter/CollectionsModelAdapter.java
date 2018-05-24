package com.cms.web.facade.adapter;

import com.cms.web.dto.CollectionsDto;
import com.cms.web.model.CollectionsModel;

public class CollectionsModelAdapter
{
  public static CollectionsDto convertToEntity(CollectionsModel model)
  {
    if(model == null)
      return null;
    CollectionsDto shortcut = new CollectionsDto();
    shortcut.setId(model.getId());
    shortcut.setName(model.getName());
    shortcut.setUrl(model.getPath());
    shortcut.setImgData(model.getImgData());
    return shortcut;
  }

  public static CollectionsModel convertToModel(CollectionsDto entity)
  {
    if(entity == null)
      return null;
    CollectionsModel shortcut = new CollectionsModel();
    shortcut.setId(entity.getId());
    shortcut.setName(entity.getName());
    shortcut.setPath(entity.getUrl());
    shortcut.setImgData(entity.getImgData());
    return shortcut;
  }
}
