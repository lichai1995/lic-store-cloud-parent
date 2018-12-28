package com.lic.service.impl;

import com.lic.mapper.ContentCategoryMapper;
import com.lic.pojo.ContentCategory;
import com.lic.pojo.ContentCategoryExample;
import com.lic.result.EgoResult;
import com.lic.result.TreeResult;
import com.lic.service.ContentCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(ContentCategoryServiceImpl.class);

    @Autowired
    ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<TreeResult> getTree(Long pId) {
        pId = pId == null ? 0 : pId;
        ContentCategoryExample example = new ContentCategoryExample();
        //指定条件(状态为1正常0删除)
        example.createCriteria().andParentIdEqualTo(pId).andStatusEqualTo(1);
        //执行查询
        List<ContentCategory> catList = contentCategoryMapper.selectByExample(example);
        //返回结果
        List<TreeResult> resultList = new ArrayList<>();
        if (catList != null && catList.size() > 0) {
            for (ContentCategory cat : catList) {
                TreeResult tree = new TreeResult();
                tree.setId(cat.getId().intValue());
                tree.setText(cat.getName());
                tree.setState(cat.getIsParent() == 1 ? "closed" : "open");
                resultList.add(tree);
            }
        }
        return resultList;
    }

    @Override
    public EgoResult saveOrUpdate(ContentCategory contentCategory) {
        try {
            Long id = contentCategory.getId();
            contentCategory.setUpdated(new Date());
            int res = 0;
            if (id != null && id > 0) {//id存在，更新
                res = contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
            } else {//保存
                //状态1(正常)，2(删除)
                contentCategory.setStatus(1);
                //排序序号
                contentCategory.setSortOrder(1);
                //该类目是否为父类目，1为true，0为false
                contentCategory.setIsParent((byte) 0);
                contentCategory.setCreated(new Date());
                res = contentCategoryMapper.insertSelective(contentCategory);
                ContentCategory parent = new ContentCategory();
                parent.setId(contentCategory.getParentId());
                parent.setIsParent((byte) 1);
                contentCategoryMapper.updateByPrimaryKeySelective(parent);
            }
            if (1 == res) {
                return EgoResult.success();
            }
        } catch (Exception e) {
            logger.error("保存或更新失败,原因" + e.getMessage());
        }
        return EgoResult.fail();
    }

    @Override
    public EgoResult deleteById(Long id) {
        try{
            ContentCategory contentCategory = new ContentCategory();
            contentCategory.setId(id);
            contentCategory.setStatus(0);
            //删除当前选中
            contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
            deleteChildren(id);
            return EgoResult.success();
        }catch(Exception e){
            logger.error("内容删除失败,原因"+e.getMessage());
        }
        return EgoResult.fail();
    }
    /**
     * 递归删除子项目
     *
     * @param id
     */
    private void deleteChildren(Long id) {
        ContentCategoryExample example = new ContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(id);
        //查询出所有的子节点
        List<ContentCategory> children = contentCategoryMapper.selectByExample(example);
        if (children != null && children.size() > 0) {
            for (ContentCategory cat : children) {
                if (cat.getIsParent() == 1) {
                    deleteById(cat.getId());
                }
                cat.setStatus(0);
                contentCategoryMapper.updateByPrimaryKeySelective(cat);
            }
        }
    }
}
