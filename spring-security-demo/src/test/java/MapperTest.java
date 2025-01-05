import com.zhengwei.security.SecurityDemo;
import com.zhengwei.security.domain.SysUser;
import com.zhengwei.security.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SecurityDemo.class)
public class MapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void test() {
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        System.out.println(sysUsers);
    }
}
