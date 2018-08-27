
package com.redis.demo.redisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

        /**
         * 对外提供静态方法
         * @return
         */
        public static Jedis getJedis(){
            Jedis jedis = pool.getResource();
          //  jedis.auth("redis123");
            return jedis;
        }
        /**
         * 得到Key
         * @param key
         * @return
         */
        public String buildKey(String key) {

            return key;

        }
        public boolean exist(String key) {

            String bKey = buildKey(key);

            if(getJedis() == null || !getJedis().exists(bKey)) {

                return false;
            }
            return true;
        }
        /**
         * 向缓存中设置对象
         *
         * @param key
         * @param param
         */
        public<T> void setString(String key, String param) {

            String bKey = buildKey(key);

            try{

                getJedis().set(bKey.getBytes(),SerializeUtil.serialize(param));

            }catch (Exception e) {
            }
        }
        /**
         * 获取String值
         *
         * @param key
         * @return value
         */
        public String getString(String key) {

            String bKey = buildKey(key);

            if(getJedis() == null || !getJedis().exists(bKey)) {

                return null;

            }

            return getJedis().get(bKey);

        }
        /**
         * 向缓存中设置对象
         *
         * @param key
         * @param bean
         */
        public<T> void setBean(String key, Object bean) {

            String bKey = buildKey(key);

            try{

                getJedis().set(bKey.getBytes(),SerializeUtil.serialize(bean));

            }catch (Exception e) {
            }

        }
        /**
         * 根据key 获取对象
         *
         * @param key
         * @return
         */
        @SuppressWarnings("unchecked")
        public<T> T getBean(String key) {

            String bKey = buildKey(key);

            if(getJedis() == null || !getJedis().exists(bKey.getBytes())) {

                return null;

            }

            byte[]in = getJedis().get(bKey.getBytes());

            T bean = (T) SerializeUtil.unserialize(in);

            return bean;





        }
        /**
         * 设置 list
         *
         * @param <T>
         * @param key
         * @param list
         */

        public<T> void setList(String key, List<T> list) {

            String bKey = buildKey(key);

            try{

                getJedis().set(bKey.getBytes(),SerializeUtil.serialize(list));

            }catch (Exception e) {
            }

        }

        /**
         * 获取list
         *
         * @param <T>
         * @param key
         * @return list
         */

        @SuppressWarnings("unchecked")
        public<T> List<T> getList(String key) {

            System.out.println(key);
            String bKey = buildKey(key);

            System.out.println(bKey);
            if(getJedis() == null || !getJedis().exists(bKey.getBytes())) {

                return null;

            }

            byte[]in = getJedis().get(bKey.getBytes());

            List<T>list = (List<T>) SerializeUtil.unserialize(in);

            return list;

        }
        /**
         * 设置 map
         * @param key
         * @param map
         */

        public<T> void setMap(String key, Map<String, T> map) {

            String bKey = buildKey(key);

            try{

                getJedis().set(bKey.getBytes(),SerializeUtil.serialize(map));

            }catch (Exception e) {
            }

        }
        /**
         * 获取list
         * @param <T>
         * @param key
         * @return list
         */

        @SuppressWarnings("unchecked")
        public<T> Map<String, T> getMap(String key) {

            String bKey = buildKey(key);

            if(getJedis() == null || !getJedis().exists(bKey.getBytes())) {

                return null;

            }

            byte[]in = getJedis().get(bKey.getBytes());

            Map<String,T> map = (Map<String, T>) SerializeUtil.unserialize(in);

            return map;

        }






    }

