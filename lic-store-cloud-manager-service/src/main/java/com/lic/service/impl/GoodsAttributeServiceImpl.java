package com.lic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lic.mapper.GoodsAttributeMapper;
import com.lic.pojo.GoodsAttribute;
import com.lic.pojo.GoodsAttributeExample;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.GoodsAttributeService;
import com.lic.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsAttributeServiceImpl implements GoodsAttributeService {
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    private static Logger logger = LoggerFactory.getLogger(GoodsAttributeServiceImpl.class);

    @Override
    public EasyuiPageResult findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        GoodsAttributeExample example = new GoodsAttributeExample();
        example.setOrderByClause("id desc");
        List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectByExample(example);
        PageInfo<GoodsAttribute> pageInfo = new PageInfo<>(goodsAttributes);
        EasyuiPageResult result = new EasyuiPageResult();
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EgoResult save(GoodsAttribute goodsAttribute) {
        try{
            int res = goodsAttributeMapper.insertSelective(goodsAttribute);
            if (1 == res) {
                return EgoResult.success();
            }
        }catch(Exception e){
            logger.error("保存商品规格参数失败"+e.getMessage());
        }
        return EgoResult.fail();
    }

    @Override
    public EgoResult updateById(GoodsAttribute goodsAttribute) {
        try{
            int res= goodsAttributeMapper.updateByPrimaryKeySelective(goodsAttribute);
            if(1 == res){
                return EgoResult.success();
            }
        }catch (Exception e){
            logger.error("更新商品规格出错"+e.getMessage());
        }
        return EgoResult.fail();
    }

    @Override
    public GoodsAttribute findById(Integer id) {
        return goodsAttributeMapper.selectByPrimaryKey(id);
    }

    @Override
    public EgoResult deleteByIds(String ids) {
        if(ids != null && ids.trim().length()>0){
            try{
                String[] idArray=ids.split(",");
                List<Integer> idList = StringUtil.arr2List(idArray);
                GoodsAttributeExample example = new GoodsAttributeExample();
                example.createCriteria().andIdIn(idList);
                goodsAttributeMapper.deleteByExample(example);
                return EgoResult.success();
            }catch(Exception e){
                logger.error("删除商品规格出错"+e.getMessage());
            }
        }
        return EgoResult.fail();
    }
}
