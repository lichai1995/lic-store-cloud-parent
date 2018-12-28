package com.lic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lic.mapper.GoodsMapper;
import com.lic.pojo.Goods;
import com.lic.pojo.GoodsExample;
import com.lic.result.EgoPageInfo;
import com.lic.result.GoodsVo;
import com.lic.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public EgoPageInfo search(Integer currentPage,Integer pageSize,String keyWords) {
        PageHelper.startPage(currentPage,pageSize);
        GoodsExample example = new GoodsExample();
        if(keyWords!=null && keyWords.trim().length()>0){
            System.out.println("hello");
            example.createCriteria().andKeywordsLike("%"+keyWords+"%");
        }
        System.out.println("关键字"+keyWords);
        List<Goods> goodsList= goodsMapper.selectByExample(example);
        List<GoodsVo> goodsVoList = new ArrayList<>();
        if(goodsList !=null && goodsList.size()>0){
            for(Goods goods:goodsList){
                GoodsVo goodsVo = new GoodsVo();
                goodsVo.setId(goods.getId());
                goodsVo.setGoodsName(goods.getGoodsName());
                goodsVo.setOriginalImg(goods.getOriginalImg());
                goodsVo.setShopPrice(String.valueOf(goods.getShopPrice()));
                goodsVoList.add(goodsVo);
            }
        }
        PageInfo<GoodsVo> pageInfo = new PageInfo<>(goodsVoList);
        EgoPageInfo result = new EgoPageInfo();
        result.setRows(pageInfo.getList());
        result.setCount(pageInfo.getTotal());
        return result;
    }
}
