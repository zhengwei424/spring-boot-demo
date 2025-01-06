import com.zhengwei.security.SecurityDemo;
import com.zhengwei.security.component.RedisCache;
import com.zhengwei.security.domain.LoginUser;
import com.zhengwei.security.domain.SysUser;
import com.zhengwei.security.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest(classes = SecurityDemo.class)
public class MapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisCache redisCache;

    @Test
    public void test1() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        bCryptPasswordEncoder.encode("123");
        // $2a$10$mDQc5cQCxwzEQxiwiaSNteoDxNzLKSSual8utvvLIn2GJhBmrcZ4a
        boolean a = bCryptPasswordEncoder.matches("123", "$2a$10$mDQc5cQCxwzEQxiwiaSNteoDxNzLKSSual8utvvLIn2GJhBmrcZ4a");
        System.out.println(a);


    }

    @Test
    public void test() {
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        System.out.println(sysUsers);
    }

    @Test
    public void testRedis() {
        LoginUser loginUser = redisCache.getCacheObject("login:2");
        System.out.println(loginUser);
    }
}
