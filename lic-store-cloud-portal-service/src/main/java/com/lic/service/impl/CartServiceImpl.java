package com.lic.service.impl;

import com.lic.mapper.GoodsMapper;
import com.lic.pojo.Goods;
import com.lic.result.CartVo;
import com.lic.result.EgoPageInfo;
import com.lic.result.EgoResult;
import com.lic.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public EgoResult addCart(Integer goodsId, Integer num, String userUUId) {
        Jedis jedis = new Jedis("192.168.245.128", 6379);
        String hashKey = userUUId + "_hash";
        try {
            String goodsNumExists = jedis.hget(hashKey, String.valueOf(goodsId));
            //设置总数量
            setTotal(num, userUUId, jedis);
            //每件商品的数量
            setHash(goodsId, num, userUUId, jedis);
            //设置列表
            setCartList(goodsId, userUUId, jedis);
            return EgoResult.success();
        } catch (Exception e) {
            logger.error("添加至购物车，原因" + e.getMessage());
            return EgoResult.fail();
        }
    }

    @Override
    public Long getCartTotal(String userUUId) {

        Jedis jedis = new Jedis("192.168.245.128", 6379);
        //总数量key
        String totalKey = userUUId + "_total";
        //设置总数量
        String totalStr = jedis.get(totalKey);
        if (totalStr != null && totalStr.trim().length() > 0) {
            return Long.parseLong(totalStr);
        }
        return 0L;
    }

    @Override
    public EgoPageInfo findByPage(Integer currentPage, Integer pageSize, String userUUId) {
        Jedis jedis = new Jedis("192.168.245.128", 6379);
        //每件商品对应数量
        String hashKey = userUUId + "_hash";
        //列表key
        String listKey = userUUId + "_list";
        Integer start = (currentPage - 1) * pageSize;
        Integer end = start + pageSize - 1;
        //分页查询
        Set<Tuple> tupleSet = jedis.zrevrangeWithScores(listKey, start, end);
        //总条数
        Long count = jedis.zcard(listKey);
        List<CartVo> cartVoList = new ArrayList<>();
        for (Tuple tuple : tupleSet) {
            String goodsStr = tuple.getElement();
            String cartNumStr = jedis.hget(hashKey, goodsStr);
            Integer goodsId = Integer.parseInt(goodsStr);
            Integer carNum = Integer.parseInt(cartNumStr);
            //获取购物车常用信息
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            CartVo cartVo = new CartVo();
            BeanUtils.copyProperties(goods, cartVo);
            cartVo.setCartNum(carNum);
            cartVoList.add(cartVo);
        }
        EgoPageInfo pageInfo = new EgoPageInfo(currentPage, pageSize, count);
        pageInfo.setRows(cartVoList);
        return pageInfo;
    }

    @Override
    public EgoResult deleteGoods(Integer goodsId, String userUUId) {
        Jedis jedis = new Jedis("192.168.245.128", 6379);
        //总数量key
        String totalKey = userUUId + "_total";
        //每件商品对应数量
        String hashKey = userUUId + "_hash";
        //列表key
        String listKey = userUUId + "_list";
        //删除列表
        jedis.zrem(listKey,String.valueOf(goodsId));
        //删除hash
        String cartNumStr=jedis.hget(hashKey,String.valueOf(goodsId));
        jedis.hdel(hashKey,String.valueOf(goodsId));
        //更新总数量
        String totalStr = jedis.get(totalKey);
        Integer total = Integer.parseInt(totalStr);
        Integer cartNum = Integer.parseInt(cartNumStr);
        jedis.set(totalKey,String.valueOf(total-cartNum));
        return EgoResult.success();
    }

    @Override
    public EgoResult deleteAll(String userUUId) {
        Jedis jedis = new Jedis("192.168.245.128", 6379);
        String totalKey = userUUId + "_total";
        //每件商品对应数量
        String hashKey = userUUId + "_hash";
        //列表key
        String listKey = userUUId + "_list";
        jedis.del(totalKey,hashKey,listKey);
        return EgoResult.success();
    }

    /**
     * 设置列表
     *
     * @param goodsId
     * @param userUUId
     * @param jedis
     */
    private void setCartList(Integer goodsId, String userUUId, Jedis jedis) {
        //列表
        String listKey = userUUId + "_list";
        //自增的key
        String incrKey = userUUId + "_incr";
        Map<String, Double> scoreMembers = new HashMap<>();
        scoreMembers.put(String.valueOf(goodsId), jedis.incr(incrKey).doubleValue());
        jedis.zadd(listKey, scoreMembers);
    }

    /**
     * 每件商品的数量
     *
     * @param goodsId
     * @param num
     * @param userUUId
     * @param jedis
     */
    private void setHash(Integer goodsId, Integer num, String userUUId, Jedis jedis) {
        //每件商品对应数量
        String hashKey = userUUId + "_hash";
        String goodsNumStr = jedis.hget(hashKey, String.valueOf(goodsId));
        if (goodsNumStr != null && goodsNumStr.trim().length() > 0) {
            Integer goodsNum = Integer.parseInt(goodsNumStr);
            jedis.hset(hashKey, String.valueOf(goodsId), String.valueOf(num + goodsNum));
        } else {
            jedis.hset(hashKey, String.valueOf(goodsId), String.valueOf(num));
        }
    }

    /**
     * 设置总数量
     *
     * @param num
     * @param userUUId
     * @param jedis
     */
    private void setTotal(Integer num, String userUUId, Jedis jedis) {
        //总数量key
        String totalKey = userUUId + "_total";
        //设置总数量
        String totalStr = jedis.get(totalKey);
        Integer total = num;
        if (totalStr != null && totalStr.trim().length() > 0) {
            total += Integer.parseInt(totalStr);
        }
        jedis.set(totalKey, String.valueOf(total));
    }
}
