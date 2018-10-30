package cn.sxh.pojo;

import lombok.Data;

/**
 * @Description:
 * @author: sxh
 * @Date: 2018/10/24
 */

@Data
public class UserPojo {
    private Long id;						// 自增长主键
    private String username;					// 登录的账号

    private String plainPassword; 			// 登录时的密码，不持久化到数据库
    private String password;				// 加密后的密码
    private String salt;					// 用于加密的盐
    private String iphone;					// 手机号
    private String email;					// 邮箱
    private String createdDate;				// 用户注册时间
    private String updatedDate;				// 用户最后一次登录时间
}
