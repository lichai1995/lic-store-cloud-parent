package com.lic.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chai
 * @Data 18/04/28 15:14
 */
@Data
public class GoodsCategoryVo implements Serializable{

    /**
     * 商品分类id
     */
    private Short id;

    /**
     * 商品分类名称
     */
    private String name;

    /**
     * 子分类
     */
    private List<GoodsCategoryVo> children;

}
