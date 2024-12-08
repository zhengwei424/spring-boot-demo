package com.zhengwei.mybatis.service.impl;

import com.zhengwei.mybatis.model.User;
import com.zhengwei.mybatis.dao.UserMapper;
import com.zhengwei.mybatis.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengwei
 * @since 2024-12-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
