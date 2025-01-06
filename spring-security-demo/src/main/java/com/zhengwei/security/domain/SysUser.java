package com.zhengwei.security.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号状态（0正常 1停用）
     */
    private String status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phonenumber;

    /**
     * 用户性别（0 男 1 女 2 未知）
     */
    private String gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型（0 管理员 1 普通用户）
     */
    private String userType;

    /**
     * 创建者id
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者id
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标志（0 未删除 1 已删除）
     */
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}