package com.cms.web.model;

public enum NewsType
{
  SPORTS("sports", "news_sports.ftl"),
  SOCIAL("social", "news_social.ftl");

  String value;
  String template;

  NewsType(String value, String template)
  {
    this.value = value;
    this.template = template;
  }

  @Override
  public String toString()
  {
    return this.value;
  }

  public String getValue()
  {
    return value;
  }

  public String getTemplate()
  {
    return template;
  }

}
