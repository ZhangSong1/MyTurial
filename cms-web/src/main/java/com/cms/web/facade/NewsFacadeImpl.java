package com.cms.web.facade;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.web.dto.MyPageDto;
import com.cms.web.dto.NewsDto;
import com.cms.web.facade.adapter.NewsModelAdapter;
import com.cms.web.freemark.view.FreeMarkerViewUtil;
import com.cms.web.model.MyPageModel;
import com.cms.web.model.NewsModel;
import com.cms.web.model.NewsStatus;
import com.cms.web.service.FileService;
import com.cms.web.service.NewsService;
import com.cms.web.util.WebUtils;

@Component
public class NewsFacadeImpl implements NewsFacade
{

  private NewsService newsService;
  private FileService fileService;

  @Autowired
  public NewsFacadeImpl(NewsService newsService, FileService fileService)
  {
    this.newsService = newsService;
    this.fileService = fileService;
  }

  @Override
  public void save(NewsModel model, HttpServletRequest request)
  {
    Map<String, Object> map = new HashMap<String, Object>();
    BeanMap beanMap = new BeanMap(model);
    for(Object key : beanMap.keySet())
    {
      map.put((String)key, beanMap.get(key));
    }
    String newsName = generateFileName();
    String date = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(new Date());
    createParentDir(StringUtils.join("F:", "/news/", date, "/", newsName, ".html"));

    String filePath = StringUtils.join(request.getHeader("Origin"), "/news/", date, "/", newsName, ".html");
    String htmlContents = FreeMarkerViewUtil.createHtml(model.getType().getTemplate(), map, request.getServletContext());
    if(fileService.save(filePath, htmlContents))
    {
      model.setStatus(NewsStatus.NEW);
      NewsDto news = NewsModelAdapter.convertToEntity(model);
      news.setName(newsName);
      news.setRealPath(filePath);
      newsService.save(news);
    }
  }

  @Override
  public void update(NewsModel model, HttpServletRequest request)
  {
    Map<String, Object> map = new HashMap<String, Object>();
    BeanMap beanMap = new BeanMap(model);
    for(Object key : beanMap.keySet())
    {
      map.put((String)key, beanMap.get(key));
    }
    String htmlContents = FreeMarkerViewUtil.createHtml(model.getType().getTemplate(), map, request.getServletContext());
    String url = getRealPathByDoc(model.getDoc());
    if(fileService.save(url, htmlContents))
    {
      model.setStatus(NewsStatus.NEW);
      NewsDto news = NewsModelAdapter.convertToEntity(model);
      news.setRealPath(url);
      newsService.save(news);
    }

  }

  private String generateFileName()
  {
    return "doc-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
  }

  @SuppressWarnings("unchecked")
  @Override
  public MyPageModel<NewsModel> getPageableNews(int pageIndex, int pageSize)
  {
    MyPageDto results = newsService.getPageableNews(pageIndex, pageSize);
    MyPageModel<NewsModel> news = new MyPageModel<NewsModel>();
    news.setTotalPages(results.getTotalPages());
    news.setTotalElements(results.getTotalElements());
    news.setResults((List<NewsModel>)CollectionUtils.collect(results.getResults(), new NewsModelAdapter()));
    return news;
  }

  @Override
  public String getRealPathByDoc(String doc)
  {
    return newsService.findByDoc(doc).getRealPath();
  }

  @Override
  public void deleteByDoc(String doc)
  {
    newsService.deleteByDoc(doc);
  }

  @Override
  public NewsModel queryByDoc(String doc)
  {
    NewsModel model = null;
    NewsDto entity = newsService.findByDoc(doc);
    if(entity != null)
    {
      String realPath = entity.getRealPath();
      model = NewsModelAdapter.convertToVO(entity);
      model.setContent(WebUtils.paserBody(fileService.getContet(realPath)));
    }

    return model;
  }

  @Override
  public boolean saveImage(String url, byte[] data)
  {

    return fileService.save(url, new String(data));

  }

  private static void createParentDir(String filePath)
  {
    File file = new File(filePath);
    if(file.getParentFile().exists())
    {
      return;
    }
    else
    {
      try
      {
        FileUtils.forceMkdirParent(file);
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
    }
  }

}
