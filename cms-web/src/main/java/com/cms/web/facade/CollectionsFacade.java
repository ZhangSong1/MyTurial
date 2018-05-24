package com.cms.web.facade;

import java.util.List;

import com.cms.web.model.CollectionsModel;

public interface CollectionsFacade
{
  public void save(CollectionsModel model);
  public List<CollectionsModel> queryAll();
  public void delete(int id);
}
