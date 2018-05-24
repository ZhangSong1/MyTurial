package com.cms.web.model;

public enum NewsStatus
{
  NEW("new"),
  PUBLISHED("published");
  String value;

  private NewsStatus(String value)
  {
    this.value = value;
  }

  @Override
  public String toString()
  {
    return this.value;
  }
}
