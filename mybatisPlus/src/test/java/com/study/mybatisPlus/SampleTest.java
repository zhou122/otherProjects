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

    /*@Test
    public void aInsert() {
        User user = new User();
        user.setNew_name("咩咩");
        *//*user.setNewName("咩咩");*//*
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
                .lambda().eq(User::getNew_name, "Sandy")));
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
        Assert.assertEquals("Jack", user.getNew_name());
        Assert.assertTrue(20 == user.getAge());
    }*/

    @Test
    public void selectAll(){
        User iUser = new User();
/*        iUser.setId(11111111111L);*/
        iUser.setOldName("给的价格的");
        iUser.setNew_name("高低杠大概");
        iUser.setAge(131);
        iUser.setEmail("gdfgdgdg");
        userMapper.insert(iUser);

        List<User> users = userMapper.selectAll();
        if(users==null|| users.isEmpty()){
            System.out.println("=====查询结果为空");
            return;
        }
        for(User user:users){
            System.out.println("id:"+user.getId()+",old_name:"+user.getOldName()+",new_name:"+user.getNew_name()+",age:"+user.getAge()+",email:"+user.getEmail());
        }
        System.out.println("=============================");
        users = userMapper.selectAllWithResultMap();
        if(users==null|| users.isEmpty()){
            System.out.println("=====查询结果为空");
            return;
        }
        for(User user:users){
            System.out.println("id:"+user.getId()+",old_name:"+user.getOldName()+",new_name:"+user.getNew_name()+",age:"+user.getAge()+",email:"+user.getEmail());
        }
    }

/*    @Test
    public void deleteByMap(){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("old_name","Jone");
        int rows = userMapper.deleteByMap(paramMap);
        System.out.println("=====删除行数:"+rows);
    }*/
}
