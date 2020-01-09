package com.cn.jedisutil.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {
  private static JedisPool jp = null;
  static String host;
  static int  port;
  static int maxTotal;
  static int maxIdle;

  static {
    //读取配置文件
    ResourceBundle rb = ResourceBundle.getBundle("redis");
    host = rb.getString("redis.host");
    port = Integer.parseInt(rb.getString("redis.port"));
    maxTotal = Integer.parseInt(rb.getString("redis.maxTotal"));
    maxIdle = Integer.parseInt(rb.getString("redis.maxIdle"));
    System.out.println(host+port+maxIdle+maxTotal);
    JedisPoolConfig jpc = new JedisPoolConfig();
    jpc.setMaxIdle(maxIdle);//最大等待连接数量
    jpc.setMaxTotal(maxTotal);//最大连接数量

    jp = new JedisPool(jpc, host, port);
  }


  //jedis连接池
  public static Jedis getJedis() {
    return jp.getResource();
  }
}
