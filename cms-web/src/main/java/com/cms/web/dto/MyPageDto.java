package com.cms.web.dto;

import java.io.Serializable;
import java.util.List;

public class MyPageDto implements Serializable
{

  private static final long serialVersionUID = 1L;
  private int totalPages;
  private long totalElements;
  private List<NewsDto> results;

  public int getTotalPages()
  {
    return totalPages;
  }

  public long getTotalElements()
  {
    return totalElements;
  }

  public void setTotalPages(int totalPages)
  {
    this.totalPages = totalPages;
  }

  public void setTotalElements(long totalElements)
  {
    this.totalElements = totalElements;
  }

  public List<NewsDto> getResults()
  {
    return results;
  }

  public void setResults(List<NewsDto> results)
  {
    this.results = results;
  }

}
