package com.test;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class TestSign
{
  /** 数字签名，密钥算法 **/
  public static final String KEY_ALGORITHM = "RSA";
  /**
   * RSA密钥长度
   * 默认1024位
   * 密钥长度必须是64的倍数
   * 范围在512至65536之间
   **/
  private static final int KEY_SIZE = 512;
  /** 数字签名，签名/验证算法 **/
  public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

  public static void main(String[] args)
  {
    try
    {
      // 生成密钥对
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
      keyPairGen.initialize(KEY_SIZE);
      KeyPair keyPair = keyPairGen.generateKeyPair();
      RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
      RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
      String str = "ceshi";
      byte[] signature = sign(str.getBytes(), rsaPrivateKey.getEncoded());
      boolean result = verify(str.getBytes(), signature, rsaPublicKey.getEncoded());
      System.out.println("验签结果为:" + result);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * @param data
   *          待签名数据
   * @param key
   *          签名私钥
   * @return 签名
   * @throws Exception
   */
  public static byte[] sign(byte[] data, byte[] key) throws Exception
  {
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    Signature signture = Signature.getInstance(SIGNATURE_ALGORITHM);
    signture.initSign(privateKey);
    signture.update(data);
    return signture.sign();
  }

  /**
   * @param data
   *          待验签数据
   * @param sign
   *          签名数据
   * @param publicKey
   *          公钥
   * @return 返回验签结果，成功true,失败false
   * @throws Exception
   */
  public static boolean verify(byte[] data, byte[] sign, byte[] publicKey) throws Exception
  {
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initVerify(pubKey);
    signature.update(data);
    return signature.verify(sign);
  }
}
