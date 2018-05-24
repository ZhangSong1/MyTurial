package com.cms.web.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

/***
 * 对图片进行操作
 * 
 * 
 */
public class ImageHelper
{

  private static ImageHelper imageHelper = null;

  public static ImageHelper getImageHelper()
  {
    if(imageHelper == null)
    {
      imageHelper = new ImageHelper();
    }
    return imageHelper;
  }

  /***
   * 按指定的比例缩放图片
   * 
   * @param sourceImagePath
   *          源地址
   * @param destinationPath
   *          改变大小后图片的地址
   * @param scale
   *          缩放比例，如1.2
   */
  public static void scaleImage(String sourceImagePath, String destinationPath, double scale, String format)
  {

    File file = new File(sourceImagePath);
    BufferedImage bufferedImage;
    try
    {
      bufferedImage = ImageIO.read(file);
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();

      width = parseDoubleToInt(width * scale);
      height = parseDoubleToInt(height * scale);

      Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      // ---------- 增加下面的代码使得背景透明 -----------------
      Graphics2D graphics = outputImage.createGraphics(); 
      outputImage = graphics.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);       
      graphics.dispose();       
      graphics = outputImage.createGraphics();
       
      // ---------- 背景透明代码结束 -----------------
      graphics.drawImage(image, 0, 0, null);
      graphics.dispose();

      ImageIO.write(outputImage, format, new File(destinationPath));
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

  }

  /***
   * 将图片缩放到指定的高度或者宽度
   * 
   * @param sourceImagePath
   *          图片源地址
   * @param destinationPath
   *          压缩完图片的地址
   * @param width
   *          缩放后的宽度
   * @param height
   *          缩放后的高度
   * @param auto
   *          是否自动保持图片的原高宽比例
   * @param format
   *          图图片格式 例如 jpg
   */
  public static void scaleImageWithParams(InputStream input, OutputStream output, int width, int height, boolean auto, String format)
  {

    try
    {
      BufferedImage bufferedImage = null;
      bufferedImage = ImageIO.read(input);
      if(auto)
      {
        ArrayList<Integer> paramsArrayList = getAutoWidthAndHeight(bufferedImage, width, height);
        width = paramsArrayList.get(0);
        height = paramsArrayList.get(1);
      }

      Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
      BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      
      // ---------- 增加下面的代码使得背景透明 -----------------
      Graphics2D graphics = outputImage.createGraphics(); 
      outputImage = graphics.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
      graphics.dispose();       
      graphics = outputImage.createGraphics();
       
      // ---------- 背景透明代码结束 -----------------
      graphics = outputImage.createGraphics();
      graphics.drawImage(image, 0, 0, null);
      graphics.dispose();
      ImageIO.write(outputImage, format, output);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * 将double类型的数据转换为int，四舍五入原则
   * 
   * @param sourceDouble
   * @return
   */
  private static int parseDoubleToInt(double sourceDouble)
  {
    int result = 0;
    result = (int)sourceDouble;
    return result;
  }

  /***
   * 
   * @param bufferedImage
   *          要缩放的图片对象
   * @param width_scale
   *          要缩放到的宽度
   * @param height_scale
   *          要缩放到的高度
   * @return 一个集合，第一个元素为宽度，第二个元素为高度
   */
  private static ArrayList<Integer> getAutoWidthAndHeight(BufferedImage bufferedImage, int width_scale, int height_scale)
  {
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    double scale_w = getDot2Decimal(width_scale, width);
    double scale_h = getDot2Decimal(height_scale, height);
    if(scale_w < scale_h)
    {
      arrayList.add(parseDoubleToInt(scale_w * width));
      arrayList.add(parseDoubleToInt(scale_w * height));
    }
    else
    {
      arrayList.add(parseDoubleToInt(scale_h * width));
      arrayList.add(parseDoubleToInt(scale_h * height));
    }
    return arrayList;

  }

  /***
   * 返回两个数a/b的小数点后三位的表示
   * 
   * @param a
   * @param b
   * @return
   */
  public static double getDot2Decimal(int a, int b)
  {

    BigDecimal bigDecimal_1 = new BigDecimal(a);
    BigDecimal bigDecimal_2 = new BigDecimal(b);
    BigDecimal bigDecimal_result = bigDecimal_1.divide(bigDecimal_2, new MathContext(4));
    Double double1 = new Double(bigDecimal_result.toString());
    return double1;
  }

  public static boolean isImage(String extension)
  {
    return Arrays.asList("jpg", "png", "gif").contains(extension.toLowerCase());
  }

  public static String getImageWithBase64(InputStream input, String extension)
  {
    return getImageHeaderWithBase64(extension) + Base64.encodeBase64String(scaleImage(input, extension));
  }

  private static String getImageHeaderWithBase64(String extension)
  {
    if(extension.equalsIgnoreCase("png"))
    {
      return "data:image/png;base64,";
    }
    if(extension.equalsIgnoreCase("jpg"))
    {
      return "data:image/jpg;base64,";
    }
    if(extension.equalsIgnoreCase("gif"))
    {
      return "data:image/gif;base64,";
    }
    return null;
  }

  private static byte[] scaleImage(InputStream input, String extension)
  {
    ByteArrayOutputStream output = new ByteArrayOutputStream();

    byte[] data = null;
    ImageHelper.scaleImageWithParams(input, output, 100, 100, true, extension);
    data = output.toByteArray();
    try
    {
      output.close();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    return data;
  }

}
