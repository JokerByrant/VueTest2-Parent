package cn.sxh.dao;

import cn.sxh.dao.mapper.UserMapper;
import cn.sxh.entity.User;
import cn.sxh.entity.UserExample;
import cn.sxh.pojo.ResultPojo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @author: sxh
 * @Date: 2018/10/18
 */
@Component
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    public ResultPojo insert(User user) {
        ResultPojo resultPojo = new ResultPojo();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> userList = userMapper.selectByExample(userExample);
        if(CollectionUtils.isNotEmpty(userList)) {
            resultPojo.setSuccess(false);
            resultPojo.setRetMessage("用户名已存在！");
            resultPojo.setRetCode("500");
        } else {
            userMapper.insertSelective(user);
            resultPojo.setRetMessage("注册成功！");
            resultPojo.setEntity(user);
            resultPojo.setSuccess(true);
        }

        return resultPojo;
    }

    public ResultPojo findUser(User user) {
        ResultPojo resultPojo = new ResultPojo();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> userList = userMapper.selectByExample(userExample);
        if(CollectionUtils.isNotEmpty(userList)){
            User user1 = userList.get(0);
            if(user1.getPassword().equals(user.getPassword())){
                resultPojo.setRetMessage("登录成功！");
                resultPojo.setSuccess(true);
                resultPojo.setEntity(user1);
            } else {
                resultPojo.setRetMessage("密码不正确！");
                resultPojo.setSuccess(false);
                resultPojo.setRetCode("500");
            }

        } else {
            resultPojo.setRetMessage("该用户不存在！");
            resultPojo.setSuccess(false);
            resultPojo.setRetCode("500");
        }

        return resultPojo;

    }
}
