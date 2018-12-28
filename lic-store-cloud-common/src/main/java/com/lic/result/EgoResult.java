package com.lic.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chai
 * @Data 18/04/23 23:37
 */

@Data
public class EgoResult implements Serializable{
    //状态码
    private Integer status;
    //描述信息
    private String message;
    //成功
    public static EgoResult success(){
        EgoResult result= new EgoResult();
        result.setStatus(200);
        result.setMessage("成功");
        return result;
    }
    //失败
    public static EgoResult fail(){
        EgoResult result= new EgoResult();
        result.setStatus(400);
        result.setMessage("失败");
        return result;
    }
}
