package com.zhengwei.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhengwei.security.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
 * @createDate 2025-01-08 17:29:08
 * @Entity generator.domain.SysMenu
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	List<String> selectPermsByUserId(Long userId);
}




