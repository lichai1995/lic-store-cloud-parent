package com.lic.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EasyuiPageResult implements Serializable{
    private List<?> rows;
    private Long total;
}
