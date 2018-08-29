
package com.redis.demo.redisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

    /**
     * 操作Redis的工具类
     * @author chenrushui
     *
     */
    @Repository
    public class JedisPoolUtils {

        public final static String VIRTUAL_COURSE_PREX = "_lc_vc_";

        public static JedisPool pool=null;

        /**
         * 静态代码块只执行一次
         */
        static{
            //加载配置文件
            InputStream in = JedisPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");
            //创建配置文件对象
            Properties prop = new Properties();
            try {
                //加载文件流
                prop.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(Integer.parseInt(prop.get("redis.MaxTotal").toString()));//最大连接数，连接全部用完，进行等待
            poolConfig.setMinIdle(Integer.parseInt(prop.get("redis.MinIdle").toString())); //最小空余数
            poolConfig.setMaxIdle(Integer.parseInt(prop.get("redis.MaxIdle").toString())); //最大空余数
            pool = new JedisPool(poolConfig,prop.get("redis.host").toString(),Integer.parseInt(prop.get("redis.port").toString()));
        }

        @Bean
        public static Jedis getJedis(){
            Jedis jedis = pool.getResource();
          //  jedis.auth("redis123");
            return jedis;
        }

    }

