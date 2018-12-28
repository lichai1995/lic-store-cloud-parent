package com.lic.service.impl;

import com.lic.result.EgoResult;
import com.lic.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class CacheServiceImpl implements CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Autowired
    JedisPool jedisPool;

    @Value("${goods.category.cache.key}")
    String goodsCategoryCacheKey;


    @Override
    public EgoResult cleanGoodsCategory() {
        try{
            Jedis jedis = new Jedis("192.168.245.128",6379);
            jedis.del(SerializationUtils.serialize(goodsCategoryCacheKey));
            return EgoResult.success();
        }catch(Exception e){
            logger.error("清控商品缓存失败,原因"+e.getMessage());
        }finally {
            jedisPool.close();
        }
        return EgoResult.fail();

    }
}
