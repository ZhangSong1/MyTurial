package com.persistence.rest.config;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class MyApplication extends ResourceConfig
{

  public MyApplication()
  {
    packages("com.persistence.rest.resource","com.persistence.rest.interceptor");
    // 注册数据转换器
    register(JacksonJsonProvider.class);
    register(MultiPartFeature.class);
    register(io.swagger.jaxrs.listing.ApiListingResource.class);
    register(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class);
    register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

  }

}
