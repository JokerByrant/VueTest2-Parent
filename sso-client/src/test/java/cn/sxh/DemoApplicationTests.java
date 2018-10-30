package cn.sxh;

import cn.sxh.entity.User;
import cn.sxh.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void fun() {
//        redisUtil.set("2","lisi");
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan1");
        stringRedisTemplate.opsForValue().set("aaaa","111");

        redisUtil.set("1", user, Long.valueOf(10));
        User user1 = (User)redisUtil.get("1");
        System.out.println(user1.getUsername());

        redisUtil.set("a", "lisi", Long.valueOf(20));
        String s = (String)redisUtil.get("a");
        System.out.println(s);

        redisUtil.set("b", true, Long.valueOf(20));
        Boolean d = (Boolean)redisUtil.get("b");
        System.out.println(d);
//        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
    }


}
