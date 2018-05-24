package com.cms.web.facade.adapter;

import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.cms.web.dto.NewsDto;
import com.cms.web.model.NewsModel;
import com.cms.web.model.NewsStatus;
import com.cms.web.model.NewsType;
import com.cms.web.util.WebConstants;
import com.cms.web.util.WebUtils;
public class NewsModelAdapter implements Transformer
{
  public static NewsDto convertToEntity(NewsModel model)
  {
    if(model == null)
      return null;
    NewsDto news = new NewsDto();
    news.setTitle(model.getTitle());
    news.setAuthor(model.getAuthor());
    news.setType(model.getType().toString().toUpperCase());
    news.setStatus(model.getStatus().toString().toUpperCase());
    if(StringUtils.isNotBlank(model.getCreateDate()))
    {
      news.setCreateDate(WebUtils.StringToTimestamp(model.getCreateDate()));
    }
    if(StringUtils.isNotBlank(model.getDoc()))
    {
      news.setName(model.getDoc());
    }
    return news;
  }

  public static NewsModel convertToVO(NewsDto input)
  {
    if(input == null)
      return null;
    return (NewsModel)new NewsModelAdapter().transform(input);
  }

  @Override
  public Object transform(Object input)
  {
    NewsModel news = null;
    if(input instanceof NewsDto)
    {
      news = new NewsModel();
      NewsDto entity = (NewsDto)input;
      news.setTitle(entity.getTitle());
      news.setAuthor(entity.getAuthor());
      news.setType(NewsType.valueOf(entity.getType().toUpperCase()));
      news.setStatus(NewsStatus.valueOf(entity.getStatus().toUpperCase()));
      news.setCreateDate(DateFormatUtils.format(entity.getCreateDate(), WebConstants.DATE_FORMAT));
      if(entity.getUpdateDate() != null)
      {
        news.setUpdateDate(DateFormatUtils.format(entity.getUpdateDate(), WebConstants.DATE_FORMAT));
      }
      news.setDoc(entity.getName());
    }
    return news;
  }
}
