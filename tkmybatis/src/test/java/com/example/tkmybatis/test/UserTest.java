package com.example.tkmybatis.test;

import com.example.tkmybatis.entity.User;
import com.example.tkmybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectAll(){
        User iUser = new User();
        iUser.setId(1212121L);
        iUser.setOld_name("向欧盟在");
        iUser.setNewName("感到翻跟斗");
        iUser.setAge(13);
        iUser.setEmail("121212");
        userMapper.insert(iUser);

        List<User> users = userMapper.selectAll();
        if(users!=null){
            for(User user:users){
                System.out.println("id:"+user.getId()+",old_name:"+user.getOld_name()+",new_name:"+user.getNewName()+",age:"+user.getAge()+",email:"+user.getEmail());
            }
        }else{
            System.out.println("查询结果为空");
        }

        users = userMapper.selectAllWithResultMap();
        if(users!=null){
            for(User user:users){
                System.out.println("id:"+user.getId()+",old_name:"+user.getOld_name()+",new_name:"+user.getNewName()+",age:"+user.getAge()+",email:"+user.getEmail());
            }
        }else{
            System.out.println("查询结果为空");
        }

        /*iUser.setOld_name("向欧盟在--修改");
        userMapper.updateByPrimaryKey(iUser);

        users = userMapper.selectAll();
        if(users!=null){
            for(User user:users){
                System.out.println("id:"+user.getId()+",old_name:"+user.getOld_name()+",new_name:"+user.getNew_name()+",age:"+user.getAge()+",email:"+user.getEmail());
            }
        }else{
            System.out.println("查询结果为空");
        }*/
    }


}
