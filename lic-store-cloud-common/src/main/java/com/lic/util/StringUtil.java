package com.lic.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chai
 * @Data 18/04/24 22:47
 */
public class StringUtil {
    /**
     * 将String类型的数组，转换为List
     */
    public static List<Integer> arr2List(String strArray[]){
        List<Integer> resList=new ArrayList<>();
        if(strArray != null && strArray.length>0){
            for(String str:strArray){
                resList.add(Integer.valueOf(str));
            }
        }
        return resList;
    }

    public static List<Long> arr2ListLong(String strArray[]){
        List<Long> resList=new ArrayList<>();
        if(strArray != null && strArray.length>0){
            for(String str:strArray){
                resList.add(Long.valueOf(str));
            }
        }
        return resList;
    }
}
