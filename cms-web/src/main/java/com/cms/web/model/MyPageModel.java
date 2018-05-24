package com.cms.web.model;

import java.io.Serializable;
import java.util.List;

public class MyPageModel<T> implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int totalPages;
  private long totalElements;
  private List<T> results;

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

  public List<T> getResults()
  {
    return results;
  }

  public void setResults(List<T> results)
  {
    this.results = results;
  }
}
