package com.cms.web.facade;

import javax.servlet.http.HttpServletRequest;

import com.cms.web.model.MyPageModel;
import com.cms.web.model.NewsModel;

public interface NewsFacade
{
  public void save(NewsModel model, HttpServletRequest request);
  
  public void update(NewsModel model, HttpServletRequest request);

  public MyPageModel<NewsModel> getPageableNews(int pageIndex, int pageSize);
  
  public String getRealPathByDoc(String doc);
  
  public void deleteByDoc(String doc);
  
  public NewsModel queryByDoc(String doc);
  
  public boolean saveImage(String url,byte[] data);
}
