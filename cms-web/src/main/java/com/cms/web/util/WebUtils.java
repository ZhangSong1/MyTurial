package com.cms.web.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.regex.Pattern;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class WebUtils
{
  public static boolean isUrl(String url)
  {
    Pattern urlPattern = Pattern.compile(WebConstants.URL_REGEX);
    return urlPattern.matcher(url).find();
  }

  public static String paserBody(String html)
  {
    Parser parser = Parser.createParser(html, CharEncoding.UTF_8);
    NodeFilter nodeFilter = new NodeFilter()
    {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean accept(Node node)
      {
        if(node.getText().startsWith("div class=\"article-body\""))
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    };
    try
    {
      NodeList list = parser.parse(nodeFilter);
      if(list.size() > 0)
      {
        return list.elementAt(0).toHtml();
      }
    }
    catch(ParserException e)
    {
      e.printStackTrace();
    }
    return html;
  }

  public static Timestamp StringToTimestamp(String date)
  {
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    FastDateFormat format = FastDateFormat.getInstance(WebConstants.DATE_FORMAT);

    try
    {
      ts = Timestamp.valueOf(DateFormatUtils.format(format.parse(date), WebConstants.DB_DATE_FORMAT));
    }
    catch(ParseException e)
    {
      e.printStackTrace();
    }
    return ts;

  }

  public static String getJsp(String view)
  {
    return StringUtils.join("jsp/", view, ".jsp");
  }

  public static String getHtml(String view)
  {
    return StringUtils.join("html/", view, ".html");
  }

  public static String getLoginHtml(String view)
  {
    return StringUtils.join(view, ".html");
  }

}
