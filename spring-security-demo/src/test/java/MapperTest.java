import com.zhengwei.security.SecurityDemo;
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
}
