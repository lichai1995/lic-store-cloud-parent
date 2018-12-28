package com.lic.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsVo implements Serializable{
    private Integer id;
    private String goodsName;
    private String originalImg;
    private String shopPrice;

}
