package com.zhengwei.mybatis.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.zhengwei.mybatis.dao.UserMapper;
import com.zhengwei.mybatis.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootTest
@Service
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() throws JSONException {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        System.out.println(JSONObject.toJSONString(userList));
    }

}
