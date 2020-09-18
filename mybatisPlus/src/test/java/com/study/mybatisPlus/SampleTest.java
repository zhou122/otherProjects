package com.study.mybatisPlus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.study.mybatisPlus.entity.User;
import com.study.mybatisPlus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Active Record 演示
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void aInsert() {
        User user = new User();
        user.setNewName("咩咩");
        user.setAge(5);
        user.setEmail("miemie@mp.com");
        Assert.assertTrue(user.insert());
        // 成功直接拿会写的 ID
        System.err.println("\n插入成功 ID 为：" + user.getId());
    }


    @Test
    public void bDelete() {
        Assert.assertTrue(new User().setId(3L).deleteById());
        Assert.assertTrue(new User().delete(new QueryWrapper<User>()
                .lambda().eq(User::getNewName, "Sandy")));
    }


    @Test
    public void cUpdate() {
        Assert.assertTrue(new User().setId(1L).setEmail("ab@c.c").updateById());
        Assert.assertTrue(new User().update(new UpdateWrapper<User>().lambda()
                        .set(User::getAge, 3).eq(User::getId, 2)));
    }


    @Test
    public void dSelect() {
        Assert.assertEquals("test1@baomidou.com", new User().setId(1L).selectById().getEmail());
        User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 2));
        Assert.assertEquals("Jack", user.getNewName());
        Assert.assertTrue(20 == user.getAge());
    }

    @Test
    public void selectAll(){
        List<User> users = new User().selectAll();
        if(users==null|| users.isEmpty()){
            System.out.println("=====查询结果为空");
            return;
        }
        System.out.println("=====查询条数为:"+users.size());
    }

    @Test
    public void deleteByMap(){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("old_name","Jone");
        int rows = userMapper.deleteByMap(paramMap);
        System.out.println("=====删除行数:"+rows);
    }
}
