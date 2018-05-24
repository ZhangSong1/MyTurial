package com.cms.web.service;

import java.util.List;

import com.cms.web.dto.CollectionsDto;

public interface CollectionsService
{
  public void save(CollectionsDto collections);

  public List<CollectionsDto> queryAll();

  public void delete(int id);
}
