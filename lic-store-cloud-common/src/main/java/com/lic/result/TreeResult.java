package com.lic.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chai
 * @Date 18/04/23 22:23
 */
@Data
public class TreeResult implements Serializable{

    private Integer id;
    private String text;
    private String state;
}
