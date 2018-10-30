package cn.sxh.controller;

import cn.sxh.entity.User;
import cn.sxh.dao.UserDao;
import cn.sxh.pojo.ResultPojo;
import cn.sxh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: sxh
 * @Date: 2018/10/17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ResultPojo regist(@RequestBody User user, HttpServletRequest request) {
        ResultPojo resultPojo = userDao.insert(user);

        user = (User)resultPojo.getEntity();
        if(user != null){
//            request.getSession().setAttribute("user", user);
//            request.getSession().setAttribute("isLogin", true);
            redisUtil.set("user", user, Long.valueOf(3600));
            redisUtil.set("isLogin", true, Long.valueOf(3600));
        }
        return resultPojo;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultPojo login(@RequestBody User user, HttpServletRequest request) {
        ResultPojo resultPojo = userDao.findUser(user);

        user = (User)resultPojo.getEntity();

        if(user != null){
//            request.getSession().setAttribute("user", user);
//            request.getSession().setAttribute("isLogin", true);
            redisUtil.set("user", user, Long.valueOf(3600));
            redisUtil.set("isLogin", true, Long.valueOf(3600));
        }
        return resultPojo;
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public ResultPojo checkLogin(HttpServletRequest request) {
        ResultPojo resultPojo = new ResultPojo();

//        Boolean isLogin = (Boolean)request.getSession().getAttribute("isLogin");
        Boolean isLogin = (Boolean) redisUtil.get("isLogin");

        if (isLogin == null || !isLogin) {
            resultPojo.setSuccess(false);
            resultPojo.setRetMessage("您尚未登录！");
        } else {
            resultPojo.setSuccess(true);
            resultPojo.setRetMessage("您已登录！");
            resultPojo.setEntity(redisUtil.get("user"));
        }

        return resultPojo;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResultPojo logout(HttpServletRequest request) {
        ResultPojo resultPojo = new ResultPojo();

//        Boolean isLogin = (Boolean)request.getSession().getAttribute("isLogin");
        Boolean isLogin = (Boolean) redisUtil.get("isLogin");

        if (isLogin == null || !isLogin) {
            resultPojo.setSuccess(false);
            resultPojo.setRetMessage("您尚未登录！");
        } else {
            resultPojo.setSuccess(true);
//            request.getSession().removeAttribute("user");
//            request.getSession().setAttribute("isLogin", false);
            redisUtil.remove("user", "isLogin");
            resultPojo.setRetMessage("注销成功！");
        }

        return resultPojo;
    }


}
