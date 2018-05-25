package com.design.patterns.adapter;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Address extends JsonObject
{
  String streetName;
  String houseNumber;
  String postalCode;
  String city;

  public String getStreetName()
  {
    return streetName;
  }

  public void setStreetName(String streetName)
  {
    this.streetName = streetName;
  }

  public String getHouseNumber()
  {
    return houseNumber;
  }

  public void setHouseNumber(String houseNumber)
  {
    this.houseNumber = houseNumber;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public void setPostalCode(String postalCode)
  {
    this.postalCode = postalCode;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

}
