package com.cms.web.service;

public interface FileService
{
  public boolean save(String filePath, String content);
  public String getContet(String filePath);
}
