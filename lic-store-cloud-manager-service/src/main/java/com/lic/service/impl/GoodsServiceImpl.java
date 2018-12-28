package com.lic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lic.mapper.GoodsMapper;
import com.lic.pojo.Goods;
import com.lic.pojo.GoodsExample;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.GoodsService;
import com.lic.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author chai
 * @Data 18/04/23 23:41
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    private static final Logger logger= LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public EgoResult save(Goods goods) {
        try{
            int res= goodsMapper.insertSelective(goods);
            if(1==res){
                return EgoResult.success();
            }
            return EgoResult.fail();
        }catch (Exception e){
            return EgoResult.fail();
        }
    }

    @Override
    public EasyuiPageResult selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        GoodsExample example= new GoodsExample();
        //id倒叙查询
        example.setOrderByClause("id desc");
        List<Goods> goodsList= goodsMapper.selectByExample(example);
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        EasyuiPageResult result = new EasyuiPageResult();
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EgoResult updateById(Goods goods) {
        try{
            int res= goodsMapper.updateByPrimaryKeySelective(goods);
            if(1==res){
                return EgoResult.success();
            }
        }catch (Exception e){
            logger.error("更新商品失败,原因"+e.getMessage());
        }
        return EgoResult.fail();
    }

    @Override
    public EgoResult deleteByIds(String ids) {
        if(ids != null && ids.trim().length()>0){
            try{
                String idArray[] = ids.split(",");
                //将string类型的数组转为List
                List<Integer> idList= StringUtil.arr2List(idArray);
                GoodsExample example = new GoodsExample();
                example.createCriteria().andIdIn(idList);
                goodsMapper.deleteByExample(example);
                return EgoResult.success();
            }catch(Exception e){
                logger.error("删除商品失败,原因"+e.getMessage());
            }

        }
        return EgoResult.fail();
    }
}
