import com.zhengwei.security.SecurityDemo;
import com.zhengwei.security.component.RedisCache;
import com.zhengwei.security.domain.LoginUser;
import com.zhengwei.security.mapper.SysMenuMapper;
import com.zhengwei.security.mapper.SysUserMapper;
import io.jsonwebtoken.Claims;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.zhengwei.security.utils.JwtUtil.createJWT;
import static com.zhengwei.security.utils.JwtUtil.parseJWT;

@SpringBootTest(classes = SecurityDemo.class)
public class MapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

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
	// List<SysUser> sysUsers = sysUserMapper.selectList(null);
	// System.out.println(sysUsers);
		List<String> perms = sysMenuMapper.selectPermsByUserId(1L);
	    System.out.println(perms);
    }

    @Test
    public void testRedis() throws JSONException {
        LoginUser loginUser = redisCache.getCacheObject("login:2");
        System.out.println(loginUser);
    }

    @Test
    public void testParseJWT() {

		String jwt = createJWT("zhengwei");
		System.out.println(jwt);

		try {
			Claims a = parseJWT(jwt);
			System.out.println(a.getSubject());
			System.out.println(a.getIssuer());
			System.out.println(a.getIssuedAt());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
