package com.lic.service.impl;

import com.lic.mapper.GoodsCategoryMapper;
import com.lic.pojo.GoodsCategory;
import com.lic.pojo.GoodsCategoryExample;
import com.lic.result.GoodsCategoryVo;
import com.lic.result.TreeResult;
import com.lic.service.GoodsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;


@Service()
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private JedisPool jedisPool;

    @Value("${goods.category.cache.key}")
    String goodsCategoryCacheKey;

    public List<TreeResult> findByPId(Short pId) {
        GoodsCategoryExample example = new GoodsCategoryExample();
        if (pId != null) {
            example.createCriteria().andParentIdEqualTo(pId);
        } else {
            example.createCriteria().andLevelEqualTo((byte) 1);
        }
        //执行查询
        List<GoodsCategory> catList = goodsCategoryMapper.selectByExample(example);
        //返回结果
        List<TreeResult> resultList = new ArrayList<TreeResult>();
        if (catList != null && catList.size() > 0) {
            for (GoodsCategory cat : catList) {
                TreeResult tree = new TreeResult();
                tree.setId(cat.getId().intValue());
                tree.setText(cat.getName());
                tree.setState(cat.getLevel() == 3 ? "open" : "closed");
                resultList.add(tree);
            }
        }
        return resultList;
    }

    @Override
    public GoodsCategory findById(Short id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsCategoryVo> findMenu(Short parentId, Byte level) {
//        //优先缓存中获取
        Jedis jedis = null;
        try{
            jedis = new Jedis("192.168.245.128",6379);
            byte[] resBytes = jedis.get(SerializationUtils.serialize(goodsCategoryCacheKey));
            if (resBytes != null && resBytes.length > 0) {
                return (List<GoodsCategoryVo>) SerializationUtils.deserialize(resBytes);
            }
            //如果缓存中没有数据，从数据库查询
            List<GoodsCategoryVo> voList = findMenuFromDb(parentId,level);
            //缓存至redis
            jedis.set(SerializationUtils.serialize(goodsCategoryCacheKey),
                    SerializationUtils.serialize(voList));
            return voList;
        }catch (Exception e){
            logger.error("缓存类目失败，原因"+e.getMessage());
        }
        return null;
    }

    private List<GoodsCategoryVo> findMenuFromDb(Short parentId, Byte level) {
        parentId = parentId == null ? 0 : parentId;
        level = level == null ? 0 : level;
        GoodsCategoryExample example = new GoodsCategoryExample();
        //指定条件
        example.createCriteria().andParentIdEqualTo(parentId).andLevelEqualTo(level);
        //查询
        List<GoodsCategory> categoryList = goodsCategoryMapper.selectByExample(example);
        //返回结果
        List<GoodsCategoryVo> voList = new ArrayList<>();
        if (categoryList != null && categoryList.size() > 0) {
            for (GoodsCategory cat : categoryList) {
                GoodsCategoryVo vo = new GoodsCategoryVo();
                vo.setId(cat.getId());
                vo.setName(cat.getName());
                if (cat.getLevel() != 3) {
                    vo.setChildren(findMenuFromDb(cat.getId(), (byte) (cat.getLevel() + 1)));
                }
                voList.add(vo);
            }
        }
        return voList;
    }
}
