package com.cn.jedisutil.util;

import jdk.nashorn.internal.objects.annotations.Where;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

public class Service {
  String id;
  Long num;

  public Service(String id,Long num) {
    this.num = num;
    this.id = id;
  }

  //控制单元
  public void service() {
    Jedis jedis = JedisUtils.getJedis();
    String value = jedis.get(id);
    try {
      if (value == null) {
        jedis.setex(id, 5, Long.MAX_VALUE - num + "");
      } else {
       Long l =  jedis.incr(id);
        business(id,num-(Long.MAX_VALUE-l));
      }
    } catch (Exception e) {
      System.out.println(id+"请充值");
      return;
    } finally {
      jedis.close();
    }
  }

  //业务操作
  public void business(String id,Long l) {
    System.out.println(id+"业务执行了"+l);
  }
}

class MyThread extends Thread {
  Service sc;
  public MyThread( String id,Long num) {
    sc = new Service(id,num);
  }
  @Override
  public void run() {
    while (true) {
      sc.service();
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class main {
  public static void main(String[] args) {
    MyThread mt = new MyThread("初级会员",10L);
    MyThread mt1 = new MyThread("高级会员",30L);
    mt.start();
    mt1.start();
  }
}