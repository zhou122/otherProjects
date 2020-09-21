package com.example.tkmybatis.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 用户实体对应表 user
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
@Data
@Table(name = "t_user")
public class User implements Serializable {

    @Id
    private Long id;
    @Column(name = "new_name")
    private String newName;
    @Column(name = "old_name")
    private String old_name;
    private Integer age;
    private String email;

}
