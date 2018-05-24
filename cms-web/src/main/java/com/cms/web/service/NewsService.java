package com.cms.web.service;

import com.cms.web.dto.MyPageDto;
import com.cms.web.dto.NewsDto;

public interface NewsService
{
  public void save(NewsDto news);

  public NewsDto findByDoc(String doc);

  public void deleteByDoc(String doc);
  
  public MyPageDto getPageableNews(int pageIndex, int pageSize);
}
