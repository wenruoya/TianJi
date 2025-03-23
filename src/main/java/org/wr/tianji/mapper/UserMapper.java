package org.wr.tianji.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.wr.tianji.pojo.dto.UserPageQueryDTO;
import org.wr.tianji.pojo.entity.User;

@Mapper
public interface UserMapper {
    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into tianji.user(user_id, name, username, password, role, create_time, usernumber)" +
            "values " +
            "(#{userId},#{name},#{username},#{password},#{role},#{createTime},#{userNumber})")
    int insert(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from tianji.user where user_id = #{id}")
    void deleteById(Long id);

    /**
     * 分页查询
     */
    Page<User> pageQuery(UserPageQueryDTO userPageQueryDTO);

    /**
     * 重置密码
     * @param id
     */
    void reset(long id,String password);

    @Select("select * from tianji.user where username = #{username}")
    User selectOne(String username);
    @Select("update tianji.user set tianji.user.password = #{password} where username=#{username}")
    void update(User user);


    void changeId(Long id);

    @Select("select * from tianji.user where username=#{username}")
    User findByUsername(String username);

    @Select("select name from tianji.user where user_id=#{userid}")
    String findNameByUserId(Long userid);
}
