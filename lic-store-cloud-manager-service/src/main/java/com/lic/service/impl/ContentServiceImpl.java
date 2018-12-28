package com.lic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lic.mapper.ContentMapper;
import com.lic.pojo.Content;
import com.lic.pojo.ContentExample;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.ContentService;
import com.lic.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);


    @Autowired
    ContentMapper contentMapper;

    @Override
    public EasyuiPageResult findByPage(Integer pageNum, Integer pageSize, Long categoryId) {
        PageHelper.startPage(pageNum, pageSize);
        ContentExample example = new ContentExample();
        if (categoryId != null && categoryId > 0) {
            example.createCriteria().andCategoryIdEqualTo(categoryId);
        }
        List<Content> contentList = contentMapper.selectByExample(example);
        PageInfo<Content> pageInfo = new PageInfo<>(contentList);
        EasyuiPageResult result = new EasyuiPageResult();
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EgoResult saveOrUpdate(Content content) {
        try {
            Long id = content.getId();
            int res = 0;
            if (id != null && id > 0) {
                res = contentMapper.updateByPrimaryKeySelective(content);
            } else {
                res = contentMapper.insertSelective(content);
            }
            if (res == 1) {
                return EgoResult.success();
            }
        } catch (Exception e) {
            logger.error("更新或添加商品内容出错,原因" + e.getMessage());
        }
        return EgoResult.fail();
    }

    @Override
    public EgoResult deleteByIds(String ids) {
        try {
            if (ids != null && ids.length() > 0) {
                String[] idArray = ids.split(",");
                List<Long> idList = StringUtil.arr2ListLong(idArray);
                ContentExample example = new ContentExample();
                example.createCriteria().andIdIn(idList);
                contentMapper.deleteByExample(example);
                return EgoResult.success();
            }
        } catch (Exception e) {
            logger.error("删除商品内容出错,原因" + e.getMessage());
        }
        return EgoResult.fail();
    }

    @Override
    public List<String> findIndexSlideImg(Long categoryId) {
        ContentExample example = new ContentExample();
        //指定条件
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<Content> contentList = contentMapper.selectByExample(example);
        List<String> stringList = new ArrayList<>();
        if (contentList != null && contentList.size() > 0) {
            for (Content content : contentList) {
                stringList.add(content.getPic());
            }
        }
        return stringList;
    }
}
