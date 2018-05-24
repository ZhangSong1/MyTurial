package com.cms.web.controller;

import java.io.IOException;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cms.web.exception.CmsServiceException;
import com.cms.web.facade.NewsFacade;
import com.cms.web.model.MyPageModel;
import com.cms.web.model.NewsModel;
import com.cms.web.model.NewsStatus;
import com.cms.web.util.JSONUtils;
import com.cms.web.util.WebConstants;
import com.cms.web.util.WebUtils;

@Controller
@RequestMapping(WebConstants.NEWS_PATH)
public class NewsController
{

  private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

  private NewsFacade newsFacade;

  @Autowired
  public NewsController(NewsFacade newsFacade)
  {
    this.newsFacade = newsFacade;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView main(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize)
  {
    ModelAndView model = new ModelAndView(WebUtils.getJsp("news"));
    MyPageModel<NewsModel> list = newsFacade.getPageableNews(currentPage - 1, pageSize);
    Predicate<NewsModel> newMatched = item -> {
      if(item.getStatus() == NewsStatus.NEW)
      {
        return true;
      }
      return false;
    };
    long newCount = list.getResults().stream().filter(newMatched).count();
    Predicate<NewsModel> publishedMatched = item -> {
      if(item.getStatus() == NewsStatus.PUBLISHED)
      {
        return true;
      }
      return false;
    };
    long publishedCount = list.getResults().stream().filter(publishedMatched).count();
    model.addObject("newsList", list.getResults());
    model.addObject("totalPages", list.getTotalPages());
    model.addObject("totalNews", list.getTotalElements());
    model.addObject("currentPageIndex", currentPage);
    model.addObject("pageSize", pageSize);
    model.addObject("newStatusCount", newCount);
    model.addObject("publishedCount", publishedCount);
    return model;
  }

  @RequestMapping(value = "/review", method = RequestMethod.GET)
  public ModelAndView reviewNews(HttpServletRequest request, HttpServletResponse resopnse)
  {
    return new ModelAndView(WebUtils.getHtml("news_review"));
  }

  @RequestMapping(value = "/review", method = RequestMethod.POST)
  public ModelAndView review(@ModelAttribute NewsModel model, HttpServletRequest request, HttpServletResponse resopnse)
  {
    logger.info("Start to review news");
    ModelAndView modelView = new ModelAndView("/news_sports");
    BeanMap beanMap = new BeanMap(model);
    for(Object key : beanMap.keySet())
    {
      modelView.addObject((String)key, beanMap.get(key));
    }
    return modelView;
  }

  @RequestMapping(value = "/review/doc", method = RequestMethod.GET)
  public void reviewDoc(@RequestParam String name, HttpServletRequest request, HttpServletResponse resopnse)
  {

    String url = newsFacade.getRealPathByDoc(name);
    try
    {
      resopnse.sendRedirect(url);
    }
    catch(IOException e)
    {
      throw new CmsServiceException(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public LazyDynaBean delete(@RequestParam String name, HttpServletRequest request, HttpServletResponse resopnse)
  {
    LazyDynaBean msg = new LazyDynaBean();
    newsFacade.deleteByDoc(name);
    resopnse.setStatus(HttpStatus.OK.value());
    msg.set("code", String.valueOf(HttpStatus.OK.value()));
    msg.set("message", "delete success!");
    return msg;
  }

  @RequestMapping(value = "/query", method = RequestMethod.GET)
  @ResponseBody
  public LazyDynaBean query(@RequestParam String name, HttpServletRequest request, HttpServletResponse resopnse)
  {
    LazyDynaBean msg = new LazyDynaBean();
    msg.set("code", String.valueOf(HttpStatus.OK.value()));
    msg.set("result", JSONUtils.obj2json(newsFacade.queryByDoc(name)));
    resopnse.setStatus(HttpStatus.OK.value());
    return msg;
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public LazyDynaBean save(@ModelAttribute NewsModel model, HttpServletRequest request, HttpServletResponse resopnse)
  {
    LazyDynaBean msg = new LazyDynaBean();
    if(StringUtils.isNotBlank(model.getDoc()))
    {
      newsFacade.update(model, request);
      resopnse.setStatus(HttpStatus.OK.value());
      msg.set("code", String.valueOf(HttpStatus.OK.value()));
      msg.set("message", "update success!");
    }
    else
    {
      newsFacade.save(model, request);
      resopnse.setStatus(HttpStatus.OK.value());
      msg.set("code", String.valueOf(HttpStatus.OK.value()));
      msg.set("message", "save success!");
    }
    return msg;
  }
}
