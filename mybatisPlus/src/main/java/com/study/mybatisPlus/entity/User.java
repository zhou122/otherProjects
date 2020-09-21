package com.study.mybatisPlus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户实体对应表 user
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("t_user")
public class User extends Model<User> {
    private Long id;
    @TableField("old_name")
    private String oldName;
    @TableField("new_name")
    private String new_name;
    private Integer age;
    private String email;

    /*@Override
    protected Serializable pkVal() {
        *//**
         * AR 模式这个必须有，否则 xxById 的方法都将失效！
         * 另外 UserMapper 也必须 AR 依赖该层注入，有可无 XML
         *//*
        return id;
    }*/
}
