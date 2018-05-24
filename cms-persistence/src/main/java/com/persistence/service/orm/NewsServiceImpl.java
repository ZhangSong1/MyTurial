package com.persistence.service.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.persistence.dao.NewsDao;
import com.persistence.dto.MyPageDto;
import com.persistence.entity.NewsEntity;

@Service
@Transactional
public class NewsServiceImpl implements NewsService
{

  private NewsDao newsDao;

  @Autowired
  public NewsServiceImpl(NewsDao newsDao)
  {
    this.newsDao = newsDao;
  }

  @Override
  public String save(NewsEntity news)
  {
    return newsDao.save(news).getName();
  }

  @Override
  public MyPageDto<NewsEntity> queryPageableNews(int pageIndex, int pageSize)
  {
    Pageable pageable = new PageRequest(pageIndex, pageSize);
    Page<NewsEntity> news = newsDao.findAll(pageable);
    MyPageDto<NewsEntity> result = new MyPageDto<NewsEntity>();
    result.setTotalPages(news.getTotalPages());
    result.setTotalElements(news.getTotalElements());
    result.setResults(news.getContent());
    return result;
  }

  @Override
  public void deleteByName(String name)
  {
    newsDao.deleteByName(name);
    
  }

  @Override
  public NewsEntity findByName(String name)
  {
   return newsDao.findByName(name);
  }
  
}
