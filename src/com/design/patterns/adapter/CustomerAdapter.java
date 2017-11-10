package com.design.patterns.adapter;

/*
 * Adapters
 * 1.Data transfer
 * 2.Parse the information
 * 3.Convert from one type to the other type
 * Advantages
 * 1.Integrating tow incompatible interfaces
 * 2.Avoid parsing code to be part of core modules
 * 3.change in outside interface effect only in adapters and not core application modules
 */
public class CustomerAdapter extends NewCustomer implements Customer
{
  private OldCustomer oldCustomer;

  public CustomerAdapter(OldCustomer oldCustomer)
  {
    this.oldCustomer = oldCustomer;
    this.adapterData();
  }

  private void adapterData()
  {
    this.setName(oldCustomer.getName());
    this.setAge(Integer.valueOf(oldCustomer.getAge()));

    this.setAddress(convertAddress(oldCustomer.getAddress()));
  }

  private Address convertAddress(String addressStr)
  {
    Address newAddress = new Address();
    String[] address = addressStr.split(",");
    newAddress.setStreetName(address[0]);
    newAddress.setHouseNumber(address[1]);
    newAddress.setPostalCode(address[2]);
    newAddress.setCity(address[3]);
    return newAddress;
  }

  @Override
  public String toString()
  {
    return "Name:" + this.getName() + ",age:" + this.getAge() + ",address:" + this.getAddress().getStreetName() + " "
        + this.getAddress().getHouseNumber() + " " + this.getAddress().getPostalCode() + " " + this.getAddress().getCity();
  }

  public static void main(String[] args)
  {
    OldCustomer oldCustomer = new OldCustomer();
    oldCustomer.setName("Brady");
    oldCustomer.setAge("23");
    oldCustomer.setAddress("1115 SE 164th,Ave Suite 210,98683,Vancouver");

    Customer customer = new CustomerAdapter(oldCustomer);
    System.out.println(customer.toString());

  }

}
