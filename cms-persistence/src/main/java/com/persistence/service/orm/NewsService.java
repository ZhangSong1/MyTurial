package com.persistence.service.orm;

import com.persistence.dto.MyPageDto;
import com.persistence.entity.NewsEntity;

public interface NewsService
{
  public String save(NewsEntity news);
  public MyPageDto<NewsEntity> queryPageableNews(int pageIndex,int pageSize);
  public void deleteByName(String name);
  public NewsEntity findByName(String name);
}
