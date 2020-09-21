package com.example.tkmybatis.mapper;



import com.example.tkmybatis.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * 这个得有，就算不去用它否则默认不注入
 * </p>
 * <p>
 * MP 支持不需要 UserMapper.xml 这个模块演示内置 CRUD 咱们就不要 XML 部分了
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
public interface UserMapper extends Mapper<User> {

    @Select("SELECT * FROM t_user")
    List<User> selectAll();

    @Results(id = "resultMap" , value = {
            @Result(property = "newName",column = "new_name")
    })
    @Select("SELECT * FROM t_user")
    List<User> selectAllWithResultMap();
}
