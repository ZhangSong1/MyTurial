package com.cms.web.freemark.view;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cms.web.exception.CmsServiceException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerViewUtil
{

  private static final Logger logger = LoggerFactory.getLogger(FreeMarkerViewUtil.class);
  private static String templatePath;
  //private static String savePath;
  private static String encoding;

  static
  {
    ResourceBundle resource = ResourceBundle.getBundle("config");
    templatePath = File.separator.concat(resource.getString("freeMarker.template.path"));
    encoding = resource.getString("encoding");
  }

  public static String createHtml(String templateName, Map<String, Object> data, ServletContext servletContext)
  {
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
    CharArrayWriter out = null;
    try
    {
      configuration.setServletContextForTemplateLoading(servletContext, templatePath);
      configuration.setDefaultEncoding(encoding);
      configuration.setAutoFlush(false);
      Template template = configuration.getTemplate(templateName);
      out = new CharArrayWriter();
      template.process(data, out);
      return new String(out.toCharArray());
    }
    catch(Exception e)
    {
      throw new CmsServiceException("create page fail!");
    }
    finally
    {
      if(out != null)
      {
        out.close();
      }
    }
  }
}
