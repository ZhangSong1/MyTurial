package com.cms.web.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.web.dto.CollectionsDto;
import com.cms.web.facade.adapter.CollectionsModelAdapter;
import com.cms.web.model.CollectionsModel;
import com.cms.web.service.CollectionsService;

@Component
public class CollectionsFacadeImpl implements CollectionsFacade
{
  private CollectionsService collectionsService;

  @Autowired
  public CollectionsFacadeImpl(CollectionsService collectionsService)
  {
    this.collectionsService = collectionsService;
  }

  @Override
  public void save(CollectionsModel model)
  {
    collectionsService.save(CollectionsModelAdapter.convertToEntity(model));
  }

  @Override
  public List<CollectionsModel> queryAll()
  {
    List<CollectionsModel> models = new ArrayList<CollectionsModel>();

    Consumer<CollectionsDto> action = new Consumer<CollectionsDto>()
    {

      @Override
      public void accept(CollectionsDto t)
      {
        models.add(CollectionsModelAdapter.convertToModel(t));
      }

    };
    collectionsService.queryAll().parallelStream().forEach(action);
    return models;
  }

  @Override
  public void delete(int id)
  {
    collectionsService.delete(id);

  }
}
