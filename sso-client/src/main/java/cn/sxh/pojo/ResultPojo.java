package cn.sxh.pojo;

import lombok.Data;

/**
 * @Description:
 * @author: sxh
 * @Date: 2018/10/11
 */
@Data
public class ResultPojo {
    private String retCode = "200";
    private String retMessage = "";
    Object entity;
    boolean success = false;
}
