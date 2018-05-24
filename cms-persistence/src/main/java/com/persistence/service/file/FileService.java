package com.persistence.service.file;

public interface FileService
{
    public boolean save(String url,Object data);
    public String getHtmlContet(String url);
}
