package com.lic.result;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartVo implements Serializable{
    //主键
    private Integer id;
    //图片
    private String originalImg;
    //价格
    private BigDecimal shopPrice;
    //商品名称
    private String goodsName;
    //数量
    private Integer cartNum;
}
