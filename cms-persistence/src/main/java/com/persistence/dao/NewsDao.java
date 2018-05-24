package com.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.persistence.entity.NewsEntity;

public interface NewsDao extends JpaRepository<NewsEntity, String>
{
  public NewsEntity findByName(String name);
  public void deleteByName(String name);  
}
