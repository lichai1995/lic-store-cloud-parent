package com.lic.controller;

import com.google.gson.Gson;
import com.lic.pojo.Goods;
import com.lic.pojo.GoodsAttribute;
import com.lic.pojo.GoodsCategory;
import com.lic.result.EasyuiPageResult;
import com.lic.result.EgoResult;
import com.lic.service.GoodsAttributeService;
import com.lic.service.GoodsCategoryService;
import com.lic.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods/attribute")
public class GoodsAttributeController {

    @Autowired
    GoodsAttributeService goodsAttributeService;

    @Autowired
    GoodsCategoryService goodsCategoryService;

    /**
     * 公共页面跳转
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String pageStr) {
        return "goods/goods-attribute-" + pageStr;
    }

    /**
     * 分页查询
     */
    @RequestMapping("/getData")
    @ResponseBody
    public EasyuiPageResult getData(Integer page,Integer rows){
        return goodsAttributeService.findByPage(new Gson().toJson(page),new Gson().toJson(rows));
    }

    /**
     * 保存
     */
    @RequestMapping("/save/{cId}")
    @ResponseBody
    public EgoResult save(@PathVariable("cId") Short typeId,GoodsAttribute goodsAttribute){
        goodsAttribute.setTypeId(typeId);
        return goodsAttributeService.save(new Gson().toJson(goodsAttribute));
    }

    /**
     * 跳转至编辑页编辑,接受id
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        GoodsAttribute ga = goodsAttributeService.findById(new Gson().toJson(id));
        //查询商品类目
        GoodsCategory gc = goodsCategoryService.findById(new Gson().toJson(ga.getTypeId()));
        model.addAttribute("ga",ga);
        model.addAttribute("gc",gc);
        return "goods/goods-attribute-edit";
    }
    /**
     * 编辑
     */
    @RequestMapping("/update/{cat_id}")
    @ResponseBody
    public EgoResult update(@PathVariable("cat_id") Short typeId,GoodsAttribute goodsAttribute){
        goodsAttribute.setTypeId(typeId);
        return goodsAttributeService.updateById(new Gson().toJson(goodsAttribute));
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public EgoResult delete(String ids){
        return goodsAttributeService.deleteByIds(new Gson().toJson(ids));

    }

}
