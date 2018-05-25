package com.design.patterns.adapter;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.text.StrLookup;

public class NewCustomer extends JsonObject implements Customer
{
  private String name;
  private int age;
  private Address address;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public Address getAddress()
  {
    return address;
  }

  public void setAddress(Address address)
  {
    this.address = address;
  }





public static void main(String[] args){
	NewCustomer oldCustomer = new NewCustomer();
    oldCustomer.setName("Brady");
    oldCustomer.setAge(23);
    Address address=new Address();
    address.setCity("Ave Suite");
    address.setHouseNumber("1115 SE 164th");
    oldCustomer.setAddress(address);
    System.out.println(oldCustomer.toString());
    StrLookup<String> aa=StrLookup.systemPropertiesLookup();
    System.out.println(aa);
}
  
}
