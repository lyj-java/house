package com.cn.jedisutil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class JedisutilApplicationTests {

  @Test
  void contextLoads() {
  }
  @Test
  public void testjedis(){
    //1.连接redis
    Jedis jedis = new Jedis("127.0.0.1",6379);
    //2.操作redis
    jedis.set("nvshen","nihaoma");
    String nvshen = jedis.get("nvshen");
    System.out.println(nvshen);
    //3.断开连接
    jedis.close();
  }
}
